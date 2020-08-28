package com.example.degreeplanner.ui.requirements;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.degreeplanner.R;
import com.example.degreeplanner.classes.Course;
import com.example.degreeplanner.enums.GradingOption;

//import static com.example.degreeplanner.ui.requirements.RequirementsViewModel.allCourses;
//import static com.example.degreeplanner.ui.requirements.RequirementsViewModel.allCoursesData;

public class AddRequirement extends AppCompatActivity {
    String selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_requirement);

        // Close page once button is pressed
        ImageButton closeButton = findViewById(R.id.close_page_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                finish();
            }
        });

        final EditText department = findViewById(R.id.text_department);
        final EditText course_code = findViewById(R.id.text_course_code);
        final EditText units = findViewById(R.id.number_units);
        final EditText notes = findViewById(R.id.text_notes);
        final Switch passNoPass = findViewById(R.id.switch_p_np);
        final EditText preq_department = findViewById(R.id.text_preq_department);
        final EditText preq_code = findViewById(R.id.text__prereq_course_code);

        // Set up drop down spinner
        Spinner spinner = findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.add_req_options, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        // Find which category to add class to through spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                selectedItem = parent.getItemAtPosition(position).toString();
            }
            // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        // Store information once the save button is pressed
        Button saveReq = findViewById(R.id.save_button);
        saveReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // get user inputs for the requirements
                String department_text = department.getText().toString();
                String course_code_text = course_code.getText().toString();
                String units_text = units.getText().toString();
                String notes_text = notes.getText().toString();
                GradingOption pass = GradingOption.LETTER;
                String preq_department_text = preq_department.getText().toString();
                String preq_code_text = preq_code.getText().toString();

                // todo: handle empty entries
                if (passNoPass.isChecked()) {
                    pass = GradingOption.PNP;
                }
                // Create course object from info
                Course newCourse = new Course(department_text, course_code_text,
                        pass, Double.parseDouble(units_text), notes_text);
                // Add prereq if possible
                if (!preq_department_text.equals("")) {
                    Course preqCourse = new Course(preq_department_text, preq_code_text);
                    newCourse.addPrereq(preqCourse);
                }
                // Add to correct category based on spinner
                switch (selectedItem) {
                    // Add classes to correct category
                    case "Major" :
                        RequirementsViewModel.majorCourses.addCourse(newCourse);
                        break;
                    case "Minor" :
                        RequirementsViewModel.minorCourses.addCourse(newCourse);
                        break;
                    case "College" :
                        RequirementsViewModel.collegeCourses.addCourse(newCourse);
                        break;
                    case "University" :
                        RequirementsViewModel.universityCourses.addCourse(newCourse);
                        break;
                }
                // Add course to "all courses" RequirementCategory
                RequirementsViewModel.unplannedCourses.addCourse(newCourse);
                // Close activity
                finish();
            }
        });

    }


}
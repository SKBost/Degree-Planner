package com.example.degreeplanner.ui.requirements;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.degreeplanner.R;
import com.example.degreeplanner.classes.Course;
import com.example.degreeplanner.enums.GradingOption;

//import static com.example.degreeplanner.ui.requirements.RequirementsViewModel.allCourses;
//import static com.example.degreeplanner.ui.requirements.RequirementsViewModel.allCoursesData;

public class AddRequirement extends AppCompatActivity {

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

        final EditText department = (EditText) findViewById(R.id.text_department);
        final EditText course_code = (EditText) findViewById(R.id.text_course_code);
        final EditText units = (EditText) findViewById(R.id.number_units);
        final EditText notes = (EditText) findViewById(R.id.text_notes);


        Button saveReq = (Button) findViewById(R.id.save_button);
        saveReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // get user inputs for the requirements
                String department_text = department.getText().toString();
                String course_code_text = course_code.getText().toString();
                String units_text = units.getText().toString();
                String notes_text = notes.getText().toString();

                /*
                * Get all the input fields
                * Write them using an API to your database
                * Use Http handlers to write to the database
                * */

                // Create course object from info
                // todo: add info from grading option
                // todo: handle empty entries
                Course newCourse = new Course(department_text, course_code_text,
                        GradingOption.UNCOUNTED, Double.parseDouble(units_text), notes_text);
                // Add course to RequirementCategory
                // todo: have separate RequirementCategory objects for major/minor/ge/etc.
                RequirementsViewModel req_model = new RequirementsViewModel();
                req_model.allCourses.addCourse(newCourse);
                // Close activity
                finish();
            }
        });

    }


}
package com.example.projetfinal1st;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.projetfinal1st.EntityEmployee;
import com.example.projetfinal1st.Save;

public class SaveEntityEmployee {
    @Embedded
    Save save;

    @Relation(
            parentColumn = "EmployeeIdlink",
            entityColumn = "Employeeid"
    )
    EntityEmployee entityEmployee;
}

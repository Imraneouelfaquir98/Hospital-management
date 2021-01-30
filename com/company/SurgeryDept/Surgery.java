package com.company.SurgeryDept;

import java.time.LocalDateTime;
import java.util.List;

public class Surgery {
    int roomId;
    LocalDateTime start;
    LocalDateTime  end;
    int patientId;
    int surgeon;
    List<Integer> nurses;
    List<String> stock; //  todo define stock; pair <item,quantity>
}

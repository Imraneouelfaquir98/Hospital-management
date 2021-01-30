package com.company.SurgeryDept;

import java.util.LinkedList;
import java.util.Queue;

public class SurgeryMember {
    private enum Specialty {
        Surgeon, Nurse, Anesthesiologist, Scrub_Tech
    }

    private Specialty specialty;
    private String infos;
    protected Queue<Surgery> surgeries = new LinkedList<>();

}

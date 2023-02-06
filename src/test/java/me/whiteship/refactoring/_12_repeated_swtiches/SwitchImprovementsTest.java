package me.whiteship.refactoring._12_repeated_swtiches;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwitchImprovementsTest {

    @Test
    void vacationHoursSwitchStatement() {
        SwitchImprovements si = new SwitchImprovements();
        assertEquals(120, si.vacationHoursSwitchStatement("full-time"));
    }

    @Test
    void vacationHoursSwitchExpression() {
        SwitchImprovements si = new SwitchImprovements();
        assertEquals(120, si.vacationHoursSwitchExpression("full-time"));
        assertEquals(80, si.vacationHoursSwitchExpression("part-time"));
        assertEquals(32, si.vacationHoursSwitchExpression("temporal"));
        assertEquals(0, si.vacationHoursSwitchExpression("sari"));
    }

}
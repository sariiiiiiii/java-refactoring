package me.whiteship.refactoring._06_mutable_data._20_remove_setting_method;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void person() {
        Person person = new Person("sari", 10);
//        person.setId(10);
//        person.setName("keesun");
        assertEquals(10, person.getId());
        assertEquals("sari", person.getName());
//        person.setName("whiteship");
//        assertEquals("whiteship", person.getName());
    }

}
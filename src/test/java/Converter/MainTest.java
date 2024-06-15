package Converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.junit.internal.matchers.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void parseXML() {
        //given
        List<Employee> employees2 = Main.parseXML("E://New_life//JAVA//INTELLiJ_IDEA//CSV_to_JSON_MAVEN//data.xml");
        //when
        String json2 = Main.listToJson(employees2);
        //then
        //Обычная проверка
        Assertions.assertTrue(json2.contains("John") && json2.contains("Smith"));
        //с использованием Hamcrest
        assertTrue(json2.contains("John") && json2.contains("Smith"));
        assertThat(json2, anyOf(containsString("John"),containsString("Smith")));
    }

    @Test
    void parseCSV() {
        //given
        String fileName = "E://New_life//JAVA//INTELLiJ_IDEA//CSV_to_JSON_MAVEN//data.csv";
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        //when
        List<Employee> employees = Main.parseCSV(columnMapping, fileName);
        //then
        //Обычная проверка
        for (Employee e : employees) {
            Assertions.assertTrue((e.age == 25 || e.age == 23) && ("USA".equals(e.country) || "RU".equals(e.country)));
        }
        //с использованием Hamcrest
        for (Employee e : employees) {
            assertTrue((e.age == 25 || e.age == 23) && ("USA".equals(e.country) || "RU".equals(e.country)));
        }

    }


    @Test
    void writeString() {
        //given
        String fileName = "E://New_life//JAVA//INTELLiJ_IDEA//CSV_to_JSON_MAVEN//data.csv";
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        List<Employee> employees = Main.parseCSV(columnMapping, fileName);
        String json = Main.listToJson(employees);
        //when
        Main.writeString(json, "E://New_life//JAVA//INTELLiJ_IDEA//CSV_to_JSON_MAVEN//employees.json");
        //then
        //Обычная проверка
        File file = new File("E://New_life//JAVA//INTELLiJ_IDEA//CSV_to_JSON_MAVEN//employees.json");
        Assertions.assertTrue(file.exists());
        //с использованием Hamcrest
        assertThat(String.valueOf(true), file.exists());
        assertThat(employees, is(not(empty())));
    }
}
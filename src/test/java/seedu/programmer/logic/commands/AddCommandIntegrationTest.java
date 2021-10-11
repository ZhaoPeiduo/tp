package seedu.programmer.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.programmer.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.programmer.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.programmer.testutil.TypicalStudents.getTypicalProgrammerError;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.programmer.model.Model;
import seedu.programmer.model.ModelManager;
import seedu.programmer.model.UserPrefs;
import seedu.programmer.model.student.Student;
import seedu.programmer.testutil.StudentBuilder;


/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {
    private static Student validStudent;
    private static Student sampleStudentA;
    private static AddCommand sampleCommandA;
    private static AddCommand sampleCommandB;
    private Model model;

    @BeforeAll
    public static void oneTimeSetUp() {
        // Initialize sample students and Commands once before all tests
        validStudent = new StudentBuilder().build();
        sampleStudentA = new StudentBuilder().withName("Alice").build();
        Student sampleStudentB = new StudentBuilder().withName("Bob").build();
        sampleCommandA = new AddCommand(sampleStudentA);
        sampleCommandB = new AddCommand(sampleStudentB);
    }

    @BeforeEach
    public void setUp() {
        // Re-initialize model before each test
        model = new ModelManager(getTypicalProgrammerError(), new UserPrefs());
    }

    @Test

    public void execute_newStudent_success() {
        Model expectedModel = new ModelManager(model.getProgrammerError(), new UserPrefs());
        expectedModel.addStudent(validStudent);
        assertCommandSuccess(new AddCommand(validStudent), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validStudent), expectedModel);
    }

    @Test
    public void execute_duplicateStudent_throwsCommandException() {
        Student studentInList = model.getProgrammerError().getStudentList().get(0);
        assertCommandFailure(new AddCommand(studentInList), model, AddCommand.MESSAGE_DUPLICATE_STUDENT);
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        AddCommand sampleCommandACopy = new AddCommand(sampleStudentA);
        assertEquals(sampleCommandA, sampleCommandACopy);
    }

    @Test
    public void equals_differentTypes_returnsFalse() {
        assertNotEquals(1, sampleCommandA);
    }

    @Test
    public void equals_nullValue_returnsFalse() {
        assertNotEquals(null, sampleCommandA);
    }

    @Test
    public void equals_differentStudent_returnsFalse() {
        assertNotEquals(sampleCommandA, sampleCommandB);
    }
}

package seedu.programmer.model.student;

import java.util.Optional;

import seedu.programmer.commons.util.CollectionUtil;
import seedu.programmer.commons.util.StringUtil;

/**
 * Stores the details to query the students with. Each non-empty field value will be used to query
 * for the student from the list;
 */
public class QueryStudentDescriptor {
    private String name;
    private String studentId;
    private String classId;

    /**
     * Constructor with the query parameters.
     * @param name the name to be queried.
     * @param sid the student ID to be queried.
     * @param cid the class ID to be queried.
     */
    public QueryStudentDescriptor(String name, String sid, String cid) {
        this.name = name;
        this.studentId = sid;
        this.classId = cid;
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldToBeQueried() {
        return CollectionUtil.isAnyNonNull(name, studentId, classId);
    }

    private Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    private Optional<String> getStudentId() {
        return Optional.ofNullable(studentId);
    }

    private Optional<String> getClassId() {
        return Optional.ofNullable(classId);
    }

    /**
     * Returns true if {@code student} fields matches that given in {@code QueryStudentDescriptor}.
     * @param student to be tested to ascertain if the fields matches..
     * @return true if all the non-null fields matches that of the {@code student} and false otherwise.
     */
    public boolean doesStudentMatchDescriptor(Student student) {
        Optional<String> nameToBeQueried = getName();
        Optional<String> sidToBeQueried = getStudentId();
        Optional<String> cidToBeQueried = getClassId();

        Name studentName = student.getName();
        StudentId studentSid = student.getStudentId();
        ClassId studentCid = student.getClassId();

        return doesOptionalContainsField(nameToBeQueried, studentName.fullName)
                && doesOptionalContainsField(sidToBeQueried, studentSid.studentId)
                && doesOptionalContainsField(cidToBeQueried, studentCid.classId);
    }
    /**
     * Checks if {@code str} character sequence matches any of the content of the {@code optional}.
     * @param optional to check against with.
     * @param str the char sequence to be checked if contained in the {@code optional}.
     * @return true if {@code str} is contained within {@code optional} and false otherwise.
     */
    private boolean doesOptionalContainsField(Optional<String> optional, String str) {
        if (optional.isEmpty()) {
            return true;
        }

        return StringUtil.containsPhraseIgnoreCase(str, optional.get());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof QueryStudentDescriptor)) {
            return false;
        }

        // state check
        QueryStudentDescriptor e = (QueryStudentDescriptor) other;

        return getName().equals(e.getName())
                && getStudentId().equals(e.getStudentId())
                && getClassId().equals(e.getClassId());
    }
}

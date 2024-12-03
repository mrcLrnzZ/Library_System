class Student {
    private String name;
    private String studentNumber;
    private String password;

    public Student(String name, String studentNumber, String password) {
        this.name = name;
        this.studentNumber = studentNumber;
        this.password = password;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}

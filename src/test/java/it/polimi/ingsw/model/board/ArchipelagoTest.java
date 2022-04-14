package it.polimi.ingsw.model.board;

class ArchipelagoTest {
    /*
    Island i1;
    Island i2;
    Island i3;
    Island i4;
    Island i5;
    Island i6;
    Island i7;
    Island i8;
    Island i9;
    Island i10;
    Island i11;
    Island i12;

    MotherNature motherNature;
    ArrayList<Island> islands;
    Archipelago archipelago;

    @BeforeEach
    void init(){
        i1 = new Island(1);
        i2 = new Island(2);
        i3 = new Island(3);
        i4 = new Island(4);
        i5 = new Island(5);
        i6 = new Island(6);
        i7 = new Island(7);
        i8 = new Island(8);
        i9 = new Island(9);
        i10 = new Island(10);
        i11 = new Island(11);
        i12 = new Island(12);
        motherNature = new MotherNature(2);
        islands = new ArrayList<>();

        i1.addStudent(Student.RED);
        i1.addStudent(Student.RED);
        i1.addStudent(Student.YELLOW);
        i1.addStudent(Student.BLUE);
        i2.addStudent(Student.RED);
        i3.addStudent(Student.RED);
        islands.add(i1);
        islands.add(i2);
        islands.add(i3);
        islands.add(i4);
        islands.add(i5);
        islands.add(i6);
        islands.add(i7);
        islands.add(i8);
        islands.add(i9);
        islands.add(i10);
        islands.add(i11);
        islands.add(i12);
        archipelago = new Archipelago(islands, motherNature);
    }

    @Test
    void getIslands() {
        ArrayList<Island>islands2 = new ArrayList<>();

        islands2.add(i1);
        islands2.add(i2);
        islands2.add(i3);
        islands2.add(i4);
        islands2.add(i5);
        islands2.add(i6);
        islands2.add(i7);
        islands2.add(i8);
        islands2.add(i9);
        islands2.add(i10);
        islands2.add(i11);
        islands2.add(i12);
        islands = archipelago.getIslands();
        assertEquals(islands, islands2);
    }

    @Test
    void getStudentIslands() {
        ArrayList<Student> studentWanted= new ArrayList<>();
        ArrayList<Student> student;
        studentWanted.add(Student.RED);
        studentWanted.add(Student.RED);
        studentWanted.add(Student.YELLOW);
        studentWanted.add(Student.BLUE);

        student=archipelago.getStudentIslands(1);
        assertEquals(student, studentWanted);
    }

    @Test
    void getTowersTypeIsland() {
        archipelago.getIslands().get(1).setTowerColor(Tower.WHITE);
        assertEquals(Tower.WHITE, archipelago.getTowerTypeIsland(2));
    }

    @Test
    void mergeIslands() {
        archipelago.mergeIslands(2,3);
        ArrayList<Student> studentsWanted = new ArrayList<>();
        studentsWanted.add(Student.RED);
        studentsWanted.add(Student.RED);
        ArrayList<Island>islands2 = new ArrayList<>();
        islands2.add(i1);
        islands2.add(i2);
        islands2.add(i4);
        islands2.add(i5);
        islands2.add(i6);
        islands2.add(i7);
        islands2.add(i8);
        islands2.add(i9);
        islands2.add(i10);
        islands2.add(i11);
        islands2.add(i12);
        assertTrue(islands.equals(islands2));
        System.out.println(islands);
    }

    @Test
    void getNumOfIslands() {
        assertEquals(12,archipelago.getNumOfIslands());
        archipelago.mergeIslands(2,3);
        assertEquals(11, archipelago.getNumOfIslands());
        archipelago.mergeIslands(1,12);
        assertEquals(10,archipelago.getNumOfIslands());
    }*/
}
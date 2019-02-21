public class TestClassLoader {
    public static void main(String[] args) {
        ClassLoader classLoader = TestClassLoader.class.getClassLoader();
        System.out.println(classLoader.toString());
        System.out.println(classLoader.getParent().toString());
        System.out.println(classLoader.getParent().getParent());
    }
}

import java.io.File;

public class Test {
    public static void main(String[] args) {
        File dir = new File("C:\\Users\\lyx\\IdeaProjects\\CRMManagementSystem");  // 替换为你需要查看的目录路径
        printDirectoryTree(dir, "");
    }

    public static void printDirectoryTree(File dir, String indent) {
        if (dir.isDirectory()) {
            System.out.println(indent + "[D] " + dir.getName());
            for (File file : dir.listFiles()) {
                printDirectoryTree(file, indent + "  ");
            }
        } else if (dir.isFile()) {
            System.out.println(indent + "[F] " + dir.getName());
        }
    }
}

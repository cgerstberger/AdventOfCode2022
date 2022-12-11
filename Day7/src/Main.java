import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        FsDirectory root = new FsDirectory("/");
        FsDirectory currentDir = root;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            reader.readLine(); // "$ cd /" is skipped
            while(true) {
                String line = reader.readLine();
                if(line == null)
                    break;
                if(line.startsWith("$")) {
                    // command
                    String[] cmdParts = line.split(" ");
                    if(cmdParts[1].equals("cd")) {
                        // cd .. change directory
                        if(cmdParts[2].equals("..")) {
                            // switch to parent directory
                            currentDir = currentDir.getParent();
                        } else {
//                            var newDir = new FsDirectory(cmdParts[2]);
//                            if(root == null) {
//                                root = newDir;
//                                currentDir = root;
//                            }
//                            currentDir.addFsEntity(newDir);
//                            currentDir = newDir;
                            Optional<FsEntity> optionalFsEntity = currentDir.getFsEntityByName(cmdParts[2]);
                            if(optionalFsEntity.isPresent()) {
                                FsEntity myEntity = optionalFsEntity.get();
                                if(myEntity instanceof FsDirectory) {
                                    currentDir = (FsDirectory) myEntity;
                                }
                            }
                        }

                    } else if(cmdParts[1].equals("ls")) {
                        // ls .. list
                        // nothing to do here, lines are following
                    }
                } else {
                    // entries of ls command
                    String[] parts = line.split(" ");
                    if(parts[0].equals("dir")) {
                        // new directory found
                        var newDir = new FsDirectory(parts[1]);
                        currentDir.addFsEntity(newDir);
                    } else {
                        // new file found
                        var newFile = new FsFile(parts[1], Integer.parseInt(parts[0]));
                        currentDir.addFsEntity(newFile);
                    }
                }
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        root.print("");
        List<FsDirectory> directories = root.getDirectories();
        System.out.println("Total size: " + calcDirectorySizes(directories, 100000));

        int maxSize = 70000000;
        int updateSize = 30000000;
        int usedSize = root.getSize();
        int availableSize = maxSize - usedSize;
        System.out.println("Root size: " + usedSize);

        if(availableSize < updateSize) {
            int requiredSize = updateSize - availableSize;
            Optional<FsDirectory> dirToDelete =
                    directories.stream()
                        .sorted(Comparator.comparingInt(FsDirectory::getSize))
                        .filter(dir -> dir.getSize() > requiredSize)
                        .findFirst();
            if(dirToDelete.isPresent()) {
                System.out.println("RequiredSize: " + requiredSize);
                System.out.println("DirToDelete: " + dirToDelete.get().getName() + " (" + dirToDelete.get().getSize() + ")");
            }
        }
    }


    /**
     * Sums up all directory sizes, which are below a certain boundary (=maxDirSize).
     * @param maxDirSize maximum size of a directory that is added into the calculation
     * @return the sum size of all directories, which are below maxDirSize
     */
    private static int calcDirectorySizes(List<FsDirectory> fsDirectories, int maxDirSize) {
        int sizeBelowBoundary = fsDirectories.stream()
                .map(FsDirectory::getSize)
                .filter(size -> size <= maxDirSize)
                .mapToInt(size -> size)
                .sum();
        return sizeBelowBoundary;
    }
}
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FsDirectory extends FsEntity{
    private List<FsEntity> fsEntities;

    public FsDirectory(String name) {
        super(name, FsType.Directory);
        fsEntities = new ArrayList<>();
    }

    public void addFsEntity(FsEntity fsEntity) {
        fsEntities.add(fsEntity);
        fsEntity.setParent(this);
    }

    public boolean hasFsEntities() {
        return !fsEntities.isEmpty();
    }

    public Optional<FsEntity> getFsEntityByName(String name) {
        return fsEntities.stream().filter(fsEntity -> fsEntity.getName().equals(name)).findFirst();
    }

    public List<FsDirectory> getDirectories() {
        return Stream.concat(
                Stream.of(this),
                fsEntities.stream()
                    .filter(fsEntity -> fsEntity instanceof FsDirectory)
                    .map(fsEntity -> (FsDirectory) fsEntity)
                    .map(fsDirectory -> fsDirectory.getDirectories())
                    .flatMap(fsDirectories -> fsDirectories.stream()))
                .toList();
    }

    public void print(String indentation) {
        System.out.println(indentation + "- " + getName() + " (dir)");
        fsEntities.forEach(fsEntity -> {
            fsEntity.print(indentation + "  ");
        });
    }

    public int getSize() {
        int size = fsEntities.stream()
                .map(fsEntity -> fsEntity.getSize())
                .mapToInt(x -> x)
                .sum();
        return size;
    }
}

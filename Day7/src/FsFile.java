public class FsFile extends FsEntity {
    private int size;

    public FsFile(String name, int size) {
        super(name, FsType.File);
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void print(String indentation) {
        System.out.println(indentation + "- " + getName() + " (file, size=" + size + ")");
    }
}

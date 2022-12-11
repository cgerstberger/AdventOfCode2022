public abstract class FsEntity {
    private String name;
    private FsType fsType;
    private FsDirectory parent;

    public FsEntity(String name, FsType fsType) {
        this.name = name;
        this.fsType = fsType;
    }

    public String getName() {
        return name;
    }

    public FsType getFsType() {
        return fsType;
    }

    public FsDirectory getParent() {
        return parent;
    }

    public void setParent(FsDirectory parent) {
        this.parent = parent;
    }

    public abstract void print(String indentation);
    public abstract int getSize();
}

package tech.vofy.firestorenotes.objects;

public class DeletedObject<DeletedObjectType> {
    private final int position;
    private final DeletedObjectType deletedObject;

    public DeletedObject(int position, DeletedObjectType deletedObject) {
        this.position = position;
        this.deletedObject = deletedObject;
    }

    public int getPosition() {
        return position;
    }

    public DeletedObjectType getObject() {
        return deletedObject;
    }
}
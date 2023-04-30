package content_providers;

public interface UpdateContact {
    public void updateContact(String name, String number, String rowId);
    public void openEditContact(String name, String number);
}

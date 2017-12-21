import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        storage = new Resume[storage.length];
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        int i = 0;
        while (storage[i] != null) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
            i++;
        }
        return null;
    }

    void delete(String uuid) {
        int i = 0;
        while (storage[i] != null) {
            if (storage[i].uuid.equals(uuid)) {
                while (storage[i] != null) {
                    storage[i] = storage[i + 1];
                    i++;
                }
                break;
            }
            i++;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int resumeCount = 0;
        while (storage[resumeCount] != null) {
            resumeCount++;
        }
        return Arrays.copyOfRange(storage, 0, resumeCount);
    }

    int size() {
        return getAll().length;
    }
}

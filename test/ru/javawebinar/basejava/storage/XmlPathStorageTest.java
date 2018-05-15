package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serialization.XmlStreamSerializer;

public class XmlPathStorageTest extends AbstractStorageTest {
    public XmlPathStorageTest() {
        super(new PathStorage(XML_STORAGE_DIR, new XmlStreamSerializer()));
    }
}
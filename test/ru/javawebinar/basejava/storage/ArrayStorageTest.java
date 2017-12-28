package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.Assert.*;

/**
 * Created by Pradd on 27.12.2017.
 */
public class ArrayStorageTest extends AbstractArrayStorageTest {

      public ArrayStorageTest() {
          super(new ArrayStorage());
      }

}
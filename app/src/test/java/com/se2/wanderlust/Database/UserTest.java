package com.se2.wanderlust.Database;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Marcus Bätz on 26.07.2016.
 */
public class UserTest {
    private User user;
    @Before
    public void setUp() throws Exception {
        user = new User();
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals(user.getId(),-1);
    }

    @Test
    public void testSetId() throws Exception {
        user.setId(217648573);
        assertEquals(user.getId(),217648573);
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals(user.getName(),null);
    }

    @Test
    public void testSetName() throws Exception {
        user.setName("Test");
        assertEquals(user.getName(),"Test");
    }

    @Test
    public void testGetLastname() throws Exception {
        assertEquals(user.getLastname(),null);
    }

    @Test
    public void testSetLastname() throws Exception {
        user.setLastname("Test");
        assertEquals(user.getLastname(),"Test");
    }

    @Test
    public void testGetEmail() throws Exception {
        assertEquals(user.getEmail(),null);
    }

    @Test
    public void testSetEmail() throws Exception {
        user.setEmail("Test");
        assertEquals(user.getEmail(),"Test");
    }

    @Test
    public void testGetPassword() throws Exception {
        assertEquals(user.getPassword(),null);
    }

    @Test
    public void testSetPassword() throws Exception {
        user.setPassword("Test");
        assertEquals(user.getPassword(),"Test");
    }

    @Test
    public void testIsPublicPhotos() throws Exception {
        assertEquals(user.isPublicPhotos(),true);
    }

    @Test
    public void testSetPublicPhotos() throws Exception {
        user.setPublicPhotos(false);
        assertEquals(user.isPublicPhotos(),false);
    }

    @Test
    public void testGetHpa() throws Exception {
        assertEquals(user.getHpa(),0.0,0.0000001);
    }

    @Test
    public void testSetHpa() throws Exception {
        user.setHpa(123.112);
        assertEquals(user.getHpa(),123.112,0.0000001);
    }

    @Test
    public void testGetTracking_rate() throws Exception {
        assertEquals(user.getTracking_rate(),0);
    }

    @Test
    public void testSetTracking_rate() throws Exception {
        user.setTracking_rate(1234);
        assertEquals(user.getTracking_rate(),1234);
    }
}
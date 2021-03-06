package fr.univ_amu.iut.exercice6;

import org.junit.Ignore;
import org.junit.Test;

import static fr.univ_amu.iut.exercice6.ArgsException.ErrorCode.*;
import static org.junit.Assert.*;

public class ArgsTest {

    
    @Test
    public void testCreateWithNoSchemaOrArguments() throws Exception {
        Args args = new Args("", new String[0]);
        assertEquals(0, args.nextArgument());
    }

    
    @Test
    public void testWithNoSchemaButWithOneArgument() throws Exception {
        try {
            new Args("", new String[]{"-x"});
            fail();
        } catch (ArgsException e) {
            assertEquals(UNEXPECTED_ARGUMENT, e.getErrorCode());
            assertEquals('x', e.getErrorArgumentId());
        }
    }

    
    @Test
    public void testWithNoSchemaButWithMultipleArguments() throws Exception {
        try {
            new Args("", new String[]{"-x", "-y"});
            fail();
        } catch (ArgsException e) {
            assertEquals(UNEXPECTED_ARGUMENT, e.getErrorCode());
            assertEquals('x', e.getErrorArgumentId());
        }

    }

    
    @Test
    public void testNonLetterSchema() throws Exception {
        try {
            new Args("*", new String[]{});
            fail("Args constructor should have thrown exception");
        } catch (ArgsException e) {
            assertEquals(INVALID_ARGUMENT_NAME, e.getErrorCode());
            assertEquals('*', e.getErrorArgumentId());
        }
    }

    
    @Test
    public void testInvalidArgumentFormat() throws Exception {
        try {
            new Args("f~", new String[]{});
            fail("Args constructor should have thrown exception");
        } catch (ArgsException e) {
            assertEquals(INVALID_ARGUMENT_FORMAT, e.getErrorCode());
            assertEquals('f', e.getErrorArgumentId());
        }
    }

    
    @Test
    public void testSimpleBooleanPresent() throws Exception {
        Args args = new Args("x", new String[]{"-x"});
        assertEquals(true, args.getBoolean('x'));
        assertEquals(1, args.nextArgument());
    }

    
    @Test
    public void testSimpleStringPresent() throws Exception {
        Args args = new Args("x*", new String[]{"-x", "param"});
        assertTrue(args.has('x'));
        assertEquals("param", args.getString('x'));
        assertEquals(2, args.nextArgument());
    }

    
    @Test
    public void testMissingStringArgument() throws Exception {
        try {
            new Args("x*", new String[]{"-x"});
            fail();
        } catch (ArgsException e) {
            assertEquals(MISSING_STRING, e.getErrorCode());
            assertEquals('x', e.getErrorArgumentId());
        }
    }

    
    @Test
    public void testSpacesInFormat() throws Exception {
        Args args = new Args("x, y", new String[]{"-xy"});
        assertTrue(args.has('x'));
        assertTrue(args.has('y'));
        assertEquals(1, args.nextArgument());
    }

    
    @Test
    public void testSimpleIntPresent() throws Exception {
        Args args = new Args("x#", new String[]{"-x", "42"});
        assertTrue(args.has('x'));
        assertEquals(42, args.getInt('x'));
        assertEquals(2, args.nextArgument());
    }

    
    @Test
    public void testInvalidInteger() throws Exception {
        try {
            new Args("x#", new String[]{"-x", "Forty two"});
            fail();
        } catch (ArgsException e) {
            assertEquals(INVALID_INTEGER, e.getErrorCode());
            assertEquals('x', e.getErrorArgumentId());
            assertEquals("Forty two", e.getErrorParameter());
        }

    }

    
    @Test
    public void testMissingInteger() throws Exception {
        try {
            new Args("x#", new String[]{"-x"});
            fail();
        } catch (ArgsException e) {
            assertEquals(MISSING_INTEGER, e.getErrorCode());
            assertEquals('x', e.getErrorArgumentId());
        }
    }

    
    @Test
    public void testSimpleDoublePresent() throws Exception {
        Args args = new Args("x##", new String[]{"-x", "42.3"});
        assertTrue(args.has('x'));
        assertEquals(42.3, args.getDouble('x'), .001);
    }

    
    @Test
    public void testInvalidDouble() throws Exception {
        try {
            new Args("x##", new String[]{"-x", "Forty two"});
            fail();
        } catch (ArgsException e) {
            assertEquals(INVALID_DOUBLE, e.getErrorCode());
            assertEquals('x', e.getErrorArgumentId());
            assertEquals("Forty two", e.getErrorParameter());
        }
    }

    
    @Test
    public void testMissingDouble() throws Exception {
        try {
            new Args("x##", new String[]{"-x"});
            fail();
        } catch (ArgsException e) {
            assertEquals(MISSING_DOUBLE, e.getErrorCode());
            assertEquals('x', e.getErrorArgumentId());
        }
    }

    
    @Test
    public void testExtraArguments() throws Exception {
        Args args = new Args("x,y*", new String[]{"-x", "-y", "alpha", "beta"});
        assertTrue(args.getBoolean('x'));
        assertEquals("alpha", args.getString('y'));
        assertEquals(3, args.nextArgument());
    }

    
    @Test
    public void testExtraArgumentsThatLookLikeFlags() throws Exception {
        Args args = new Args("x,y", new String[]{"-x", "alpha", "-y", "beta"});
        assertTrue(args.has('x'));
        assertFalse(args.has('y'));
        assertTrue(args.getBoolean('x'));
        assertFalse(args.getBoolean('y'));
        assertEquals(1, args.nextArgument());
    }
}

package fr.univ_amu.iut.exercice3;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConvertisseurDeNombreRomainTest {

    private ConvertisseurDeNombreRomain convertisseurDeNombreRomain;

    @Before
    public void setUp() {
        convertisseurDeNombreRomain = new ConvertisseurDeNombreRomain();
    }

    @Test
    public void leChiffreRomainIDevraitDonner1() {
        int nombre = convertisseurDeNombreRomain.enNombreArabe("I");
        assertThat(nombre).isEqualTo(1);
    }

    @Test
    public void leChiffreRomainVDevraitDonner5() {
        int nombre = convertisseurDeNombreRomain.enNombreArabe("V");
        assertThat(nombre).isEqualTo(5);
    }

    @Test
    public void leChiffreRomainXDevraitDonner10() {
        int nombre = convertisseurDeNombreRomain.enNombreArabe("X");
        assertThat(nombre).isEqualTo(10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void leChiffreRomainZDevraitDonnerUneException() {
        convertisseurDeNombreRomain.enNombreArabe("Z");
    }


    @Test
    public void leChiffreRomainIIDevraitDonner2() {
        int nombre = convertisseurDeNombreRomain.enNombreArabe("II");
        assertThat(nombre).isEqualTo(2);
    }

    @Test
    public void leChiffreRomainIIIDevraitDonner3() {
        int nombre = convertisseurDeNombreRomain.enNombreArabe("III");
        assertThat(nombre).isEqualTo(3);
    }

    @Test
    public void leChiffreRomainVIDevraitDonner6() {
        int nombre = convertisseurDeNombreRomain.enNombreArabe("VI");
        assertThat(nombre).isEqualTo(6);
    }

    @Test
    public void leChiffreRomainIVDevraitDonner4() {
        int nombre = convertisseurDeNombreRomain.enNombreArabe("IV");
        assertThat(nombre).isEqualTo(4);
    }

    @Test
    public void leChiffreRomainIXDevraitDonner9() {
        int nombre = convertisseurDeNombreRomain.enNombreArabe("IX");
        assertThat(nombre).isEqualTo(9);
    }

    @Test
    public void leChiffreRomainXLIXDevraitDonner49() {
        int nombre = convertisseurDeNombreRomain.enNombreArabe("XLIX");
        assertThat(nombre).isEqualTo(49);
    }

    @Test
    public void leChiffreRomainXCIXDevraitDonner99() {
        int nombre = convertisseurDeNombreRomain.enNombreArabe("XCIX");
        assertThat(nombre).isEqualTo(99);
    }

    @Test
    public void leChiffreRomainCCCXLVIDevraitDonner346() {
        int nombre = convertisseurDeNombreRomain.enNombreArabe("CCCXLVI");
        assertThat(nombre).isEqualTo(346);
    }

    @Test
    public void leChiffreRomainCCCXCIXDevraitDonner399() {
        int nombre = convertisseurDeNombreRomain.enNombreArabe("CCCXCIX");
        assertThat(nombre).isEqualTo(399);
    }

    @Test
    public void leChiffreRomainMMMMDCCCLXXXVIIIDevraitDonner4888() {
        int nombre = convertisseurDeNombreRomain.enNombreArabe("MMMMDCCCLXXXVIII");
        assertThat(nombre).isEqualTo(4888);
    }

    @Test(expected = IllegalArgumentException.class)
    public void leChiffreRomainILDevraitDonnerUneException() {
        convertisseurDeNombreRomain.enNombreArabe("IL");
    }

    @Test(expected = IllegalArgumentException.class)
    public void leChiffreRomainICDevraitDonnerUneException() {
        convertisseurDeNombreRomain.enNombreArabe("IC");
    }

    @Test(expected = IllegalArgumentException.class)
    public void leChiffreRomainIDDevraitDonnerUneException() {
        convertisseurDeNombreRomain.enNombreArabe("ID");
    }

    @Test(expected = IllegalArgumentException.class)
    public void leChiffreRomainIMDevraitDonnerUneException() {
        convertisseurDeNombreRomain.enNombreArabe("IM");
    }

    @Test(expected = IllegalArgumentException.class)
    public void leChiffreRomainVXDevraitDonnerUneException() {
        convertisseurDeNombreRomain.enNombreArabe("VX");
    }

    @Test(expected = IllegalArgumentException.class)
    public void leChiffreRomainXDDevraitDonnerUneException() {
        convertisseurDeNombreRomain.enNombreArabe("XD");
    }

    @Test(expected = IllegalArgumentException.class)
    public void leChiffreRomainXMDevraitDonnerUneException() {
        convertisseurDeNombreRomain.enNombreArabe("XM");
    }

}

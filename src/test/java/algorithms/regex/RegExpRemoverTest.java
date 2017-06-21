package algorithms.regex;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algorithms.regex.RegExpRemover;

public class RegExpRemoverTest {
	
	private RegExpRemover remover;
	
	@Before
	public void incialize(){
		remover = new RegExpRemover();
	}
	
	@Test
	public void testLetterCRemove() {
		assertEquals("suksess",remover.letterCRemove("success"));
		assertEquals("siramik",remover.letterCRemove("ciramic"));
		assertEquals("kurkasisek",remover.letterCRemove("curckaciceck"));
		assertEquals("kkk ksek",remover.letterCRemove("ccc ccek"));
		assertEquals("k",remover.letterCRemove("ck"));
		assertEquals("se se se, k",remover.letterCRemove("ce se ce, c"));
	}
	
	@Test
	public void testDoubleLetterRemove(){
		
		assertEquals("uo", remover.doubleLetterRemove("ooo"));
		assertEquals("u", remover.doubleLetterRemove("oou"));
		assertEquals("i", remover.doubleLetterRemove("iee"));
		assertEquals("i", remover.doubleLetterRemove("i"));
		assertEquals("u", remover.doubleLetterRemove("oooo"));
		assertEquals("uokuo", remover.doubleLetterRemove("oookkooo"));
		assertEquals("k", remover.doubleLetterRemove("kkkkkkkk"));
		assertEquals("iek", remover.doubleLetterRemove("eeeeekkkkk"));
		assertEquals(" a b ", remover.doubleLetterRemove("  a  bb  "));
		assertEquals("$_3z S,.", remover.doubleLetterRemove("$$__33zz  SS,,.."));
	}
	
	@Test
	public void testLastLetterERemove(){
		
		assertEquals("Th", remover.lastLetterERemove("The"));
		assertEquals("ooo", remover.lastLetterERemove("oooe"));
		assertEquals("ooo k", remover.lastLetterERemove("oooe ke"));
		assertEquals("e", remover.lastLetterERemove("e"));
		assertEquals("e ", remover.lastLetterERemove("e "));
		assertEquals("e e e", remover.lastLetterERemove("e e e"));
		assertEquals("ee ee", remover.lastLetterERemove("eee eee"));
		assertEquals("e l l", remover.lastLetterERemove("e le le"));
		assertEquals("th, th. th?", remover.lastLetterERemove("the, the. the?"));
		assertEquals("th.", remover.lastLetterERemove("the."));
		assertEquals("\"th\"", remover.lastLetterERemove("\"the\""));
		assertEquals("th,th.", remover.lastLetterERemove("the,the."));
		assertEquals("th^th@th,th.the8, th?th!th-th#th\"", remover.lastLetterERemove("the^the@the,the.the8, the?the!the-the#th\""));
		
	}
	
	@Test
	public void testArticlesRemove(){
		String original = "An a the, apl tthe, pear ann the potatoe the? reddish th ann egg the& ?the?";
		String modified = "An a th, apl th, pear an th potato th? redish th an eg ?th?";
		
		assertEquals("apl th, pear an potato redish th an eg ",remover.articlesRemove(modified, original));		
	}
	
	@Test
	public void testModifyText(){
		assertEquals("potato redish ",remover.modifyText("*An* potatoe ?the? reddish \"a\""));
		assertEquals("sukses",remover.modifyText("success"));
		assertEquals("kakao and kofi",remover.modifyText("cacao and coffee"));
		assertEquals("s kakao and kofi and an apl sethi, a pear ",
				remover.modifyText("The, ce the cacao and a coffee and ann apple cethee, aa pear an, the "));
	}

}

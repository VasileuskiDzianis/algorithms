package algorithms.regex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpRemover {

	/*
	 * All steps 
	 * method
	 */
	
	public String modifyText (String original){
		String modified;
		
		System.out.println("Original text:" + original);
		
		//step 1
		modified = letterCRemove(original);
		System.out.println("After Step1:" + modified);
		
		//step 2
		modified = doubleLetterRemove(modified);
		System.out.println("After Step2:" + modified);
		
		//step 3
		modified = lastLetterERemove(modified);
		System.out.println("After Step3:" + modified);
		
		//step 4
		modified = articlesRemove(modified, original);
		System.out.println("After Step4:" + modified);
		
		return modified;
	}
		
	/*
	 * If text contains “ci” and “ce”, change it to “si” and “se”. If “ck” then
	 * change it to “k”. In other case replace “c” with “k”. All changes should
	 * be made in strong order left-to-right.
	 */
	public String letterCRemove(String incomingText) {
		StringBuilder outgoingText = new StringBuilder(incomingText);

		String regexC = "c([^eik]|$)"; // pattern for replacing “c” with “k”
		Pattern pattern = Pattern.compile(regexC);
		Matcher m = pattern.matcher(incomingText);

		while (m.find()) {
			outgoingText.setCharAt(m.start(), 'k');
			
			// we have to check next one char because Matcher skip it if find
			// matching
			if ((m.start() < (outgoingText.length() - 2)) && 
					(outgoingText.charAt(m.start() + 1) == 'c') && 
					(outgoingText.charAt(m.start() + 2) != 'e') && 
					(outgoingText.charAt(m.start() + 2) != 'i') && 
					(outgoingText.charAt(m.start() + 2) != 'k')) {
				
				outgoingText.setCharAt(m.start() + 1, 'k');
			}
		}

		String regexCe = "ce"; // pattern for replacing “ce” with “se”
		String regexCi = "ci"; // pattern for replacing “ci” with “si”
		String regexCk = "ck"; // pattern for replacing “ck” with “k”

		pattern = Pattern.compile(regexCe);
		m = pattern.matcher(outgoingText);
		outgoingText.replace(0, outgoingText.length(), m.replaceAll("se"));

		pattern = Pattern.compile(regexCi);
		m = pattern.matcher(outgoingText);
		outgoingText.replace(0, outgoingText.length(), m.replaceAll("si"));

		pattern = Pattern.compile(regexCk);
		m = pattern.matcher(outgoingText);
		outgoingText.replace(0, outgoingText.length(), m.replaceAll("k"));

		return outgoingText.toString();
	}

	/*
	 * If text contains “ee” then replace it by simple “i”. If “oo” then change
	 * it by “u”. In other case any double letter should be changed by one
	 * letter.
	 */
	public String doubleLetterRemove(String incomingText) {
		StringBuilder outgoingText = new StringBuilder(incomingText);
		Pattern pattern;
		Matcher m;

		String regexEE = "ee"; // pattern for replacing “ee” with “i”
		String regexOO = "oo"; // pattern for replacing “oo” with “u”
		String regexDC = "(.)\\1"; // pattern for any double character

		pattern = Pattern.compile(regexEE);
		m = pattern.matcher(outgoingText);
		outgoingText.replace(0, outgoingText.length(), m.replaceAll("i"));

		pattern = Pattern.compile(regexOO);
		m = pattern.matcher(outgoingText);
		outgoingText.replace(0, outgoingText.length(), m.replaceAll("u"));

		pattern = Pattern.compile(regexDC);

		do {
			m = pattern.matcher(outgoingText);
			outgoingText.replace(0, outgoingText.length(), m.replaceAll("$1"));//$1 - means link to (.) in regexDC
			m = pattern.matcher(outgoingText);
		} while (m.find());
		
		return outgoingText.toString();
	}

	/*
	 * Remove letter “e” in the end of each word if word length > 1 What have I
	 * to do with "eee"? Let it be "ee", delete only last letter "e"
	 */
	public String lastLetterERemove(String incomingText) {
		StringBuilder outgoingText = new StringBuilder(incomingText);
		Pattern pattern;
		Matcher m;
		
		//pattern for replacing “e” at the end of the word
		String regexLastE = "(\\we(\\W|\\h|\\v))|(\\we$)"; 
		pattern = Pattern.compile(regexLastE);
		m = pattern.matcher(incomingText);
		List<Integer> deleteInd = new ArrayList<Integer>();
		
		while (m.find()) {
			deleteInd.add(m.start() + 1);
		}
		
		//if we don't make reverse we will loose order when delete one symbol
		Collections.reverse(deleteInd);
		if (!deleteInd.isEmpty()) {
			for (int ind : deleteInd) {
				outgoingText.deleteCharAt(ind);
			}
		}

		return outgoingText.toString();
	}

	/*
	 * Remove articles “a”, “an” or “the” from text. They should be removed only
	 * if they were the words a, an, the in the original text.
	 */
	public String articlesRemove(String modified, String original) {
		StringBuilder outgoingText = new StringBuilder();
		String articleTheOrig = "(^|[^a-zA-Z])[Tt]he((\\W|\\h|\\v)|$)";
		String articleTheChanged = "(^|[^a-zA-Z])[Tt]h((\\W|\\h|\\v)|$)";
		String articleAn = "(^|[^a-zA-Z])[Aa]n((\\W|\\h|\\v)|$)";
		String articleA = "(^|[^a-zA-Z])[Aa]((\\W|\\h|\\v)|$)";

		List<String> originalList = parseStringToList(original);
		List<String> step3List = parseStringToList(modified);
		
		// add space at the begin of the line if original line has space at the same place
		if (modified.charAt(0) == ' ') {
			outgoingText.append(" ");
		}
		
		for (int i = 0; i < step3List.size(); i++) {
			
			// article "the" remove
			if (Pattern.compile(articleTheChanged).matcher(step3List.get(i)).matches()
					& Pattern.compile(articleTheOrig).matcher(originalList.get(i)).matches()) {
				
				continue;
			
			// article "an"remove
			} else if (Pattern.compile(articleAn).matcher(step3List.get(i)).matches() & 
					   Pattern.compile(articleAn).matcher(originalList.get(i)).matches()) {
				continue;
				
			// article "a"remove
			} else if (Pattern.compile(articleA).matcher(step3List.get(i)).matches() & 
					   Pattern.compile(articleA).matcher(originalList.get(i)).matches()) {
			
				continue;
			} else {
				outgoingText.append(step3List.get(i));
				if (i < step3List.size() - 1) {
					outgoingText.append(" "); // add space except the last word
				}
			}
		}
		
		// add space at the end of the line if original line has space at the
		// same place
		if (modified.charAt(modified.length() - 1) == ' '
				& (outgoingText.charAt(outgoingText.length() - 1) != ' ')) {
			outgoingText.append(" ");
		}

		return outgoingText.toString();
	}

	//this method converts string to List of separate words
	private List<String> parseStringToList(String incomingText) {
		Scanner scanner = new Scanner(incomingText);
		List<String> list = new ArrayList<String>();
		
		while (scanner.hasNext()) {
			list.add(scanner.next());
		}
		scanner.close();
		
		return list;
	}
}

package steakie;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public class Pi {

	public char[] ziffern;
	public char[] piStellen;
	public char[] surroundings;

	private InputStream input;

	public Pi() {
	}

	public void piStreamen() {
		try {
			input = new BufferedInputStream(new FileInputStream("src/steakie/res/numbers/billionPi.txt"), 1000000);
		} catch (FileNotFoundException e) { // TODO Auto-generated catch block
											// 611000000
			e.printStackTrace();
		}
	}

	public void eStreamen() {
		try {
			input = new BufferedInputStream(new FileInputStream("src/steakie/res/numbers/millionE.txt"), 2000000);
		} catch (FileNotFoundException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void einlesen() {
		String meinString;
		try {
			BufferedReader in = new BufferedReader(new FileReader("src/steakie/res/numbers/billionPi.txt"));
			try {
				while ((meinString = in.readLine()) != null) {
					piStellen = meinString.toCharArray();
					meinString = null;
					System.out.println(piStellen.length);
				}
				in.close();
			} catch (IOException e) {
				System.out.println("Read error " + e);
			}
		} catch (IOException e) {
			System.out.println("Open error " + e);
		}
	}

	long stelle = 0;

	public long piStellenStream(int wortlaenge) throws IOException {
		piStreamen();
		byte[] buffer = new byte[1000000];

		int data = input.read(buffer);

		stelle = 0;
		while (data != 0) {
			data = input.read(buffer);

			for (int i = 0; i < buffer.length; i++) {
				stelle++;
				System.out.println(data);
				if (ziffern[0] == buffer[i]) {
					for (int k = 1; k < wortlaenge; k++) {
						if (i + k < buffer.length) {
							if (ziffern[k] == buffer[i + k]) {
								if (k == wortlaenge - 1) {
									input.close();
									System.out.println(stelle);
									return stelle;
								}
							} else {
								break;
							}
						}
					}
				}
			}
			// break;
		}
		input.close();

		return 0;

	}

	public long eStellenStream(int wortlaenge) throws IOException {
		eStreamen();
		byte[] buffer = new byte[2000000];

		int data = input.read(buffer);

		// while (data != 0) {
		stelle = 0;
		for (int i = 0; i < buffer.length; i++) {
			stelle++;
			if (ziffern[0] == buffer[i]) {
				for (int k = 1; k <= wortlaenge - 1; k++) {
					if (i + k < buffer.length) {
						if (ziffern[k - 1] == buffer[i + k]) {
							if (k == wortlaenge - 1) {
								input.close();

								return stelle;
							}
						} else {
							break;
						}
					}
				}
			}
		}
		// break;
		// }
		input.close();

		return 0;

	}

	public long stelleSuchen(int wortlaenge) {

		long stelle = 0;
		for (int i = 0; i < piStellen.length; i++) {
			stelle++;
			if (ziffern[0] == piStellen[i]) {
				for (int k = 1; k <= wortlaenge; k++) {
					if (i + k < piStellen.length) {
						if (ziffern[k - 1] == piStellen[i + k]) {
							if (k == wortlaenge) {
								return stelle;
							} else {
								
							}

						} else {
							break;
						}
					} else {
						break;
					}
				}
			}
		}
		return 0;
	}

	public void setArray(char[] array) {
		ziffern = array;
	}

}

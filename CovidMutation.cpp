#include <iostream>
#include <string>

using namespace std;

/**
 * Take user's genomeSeq and kValue input, loop through
 * genomeSeq to reverse lettering for every k size chunk
 * and put mutated sequence in new String to be printed out.
 * Special cases include when the k value is 0 or less,
 * and when the k value is greater than the given
 * genome sequence length.
 */
int main() {
    string reverseSeq = ""; // initialize output to empty string.

    string genomeSeq; // variable for storing user's first line of input
    int kValue;       // variable for storing user's second line of input

    getline(cin, genomeSeq);
    cin >> kValue;

    if (kValue <= 0) {
        reverseSeq = genomeSeq;
    }
    else if (kValue < genomeSeq.length()) {
        int currentSeg = 0; // keep track of which chunk in loop
        int numChunks = genomeSeq.length() / kValue;
        int remainder = genomeSeq.length() % kValue;

        for (int h = numChunks; h > 0; h--) {
            for (int i = kValue - 1; i >= 0; i--) {
                int subStart = i + currentSeg; // for substring
                int subEnd = subStart + 1;     // for substring

                reverseSeq.append(genomeSeq.substr(subStart, subEnd - subStart));

                if (i == 0) {
                    currentSeg += kValue; // move to next chunk
                }
            }
        }

        for (int i = genomeSeq.length() - 1; i > genomeSeq.length() - 1 - remainder; i--) {
            reverseSeq.append(genomeSeq.substr(i, 1));
        }
    }
    else {
        for (int i = genomeSeq.length() - 1; i >= 0; i--) {
            reverseSeq.append(genomeSeq.substr(i, 1));
        }
    }

    cout << reverseSeq << endl;
    return 0;
}

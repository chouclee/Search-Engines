/**
 *  This class implements the document score list data structure
 *  and provides methods for accessing and manipulating them.
 *
 *  Copyright (c) 2014, Carnegie Mellon University.  All Rights Reserved.
 */

import java.util.*;

public class ScoreList {

  //  A little utilty class to create a <docid, score> object.

  protected class ScoreListEntry implements Comparable<ScoreListEntry> {
    private int docid;
    private double score;

    private ScoreListEntry(int docid, double score) {
      this.docid = docid;
      this.score = score;
    }
    
    // Implement Comparable Interface
    // Sort the matching documents by their scores, in descending order. 
    // The external document id should be a secondary sort key (i.e., for breaking ties). 
    // Smaller ids should be ranked higher (i.e. ascending order).
    public int compareTo(ScoreListEntry o) {
      if (this.score > o.score) 
        return -1;
      else if (this.score < o.score)
        return -1;
      else { // if scores are same, use external document id to compare
        String extDocIdThis = "";
        String extDocIdO = "";
        try {
          extDocIdThis = QryEval.getExternalDocid(this.docid);
          extDocIdO = QryEval.getExternalDocid(o.docid);
        } catch (Exception e) {
          //do nothing
        }
        return extDocIdThis.compareTo(extDocIdO);
      }
    }
  }

  List<ScoreListEntry> scores = new ArrayList<ScoreListEntry>();

  /**
   *  Append a document score to a score list.
   *  @param docid An internal document id.
   *  @param score The document's score.
   *  @return void
   */
  public void add(int docid, double score) {
    scores.add(new ScoreListEntry(docid, score));
  }

  /**
   *  Get the n'th document id.
   *  @param n The index of the requested document.
   *  @return The internal document id.
   */
  public int getDocid(int n) {
    return this.scores.get(n).docid;
  }

  /**
   *  Get the score of the n'th document.
   *  @param n The index of the requested document score.
   *  @return The document's score.
   */
  public double getDocidScore(int n) {
    return this.scores.get(n).score;
  }
}

package uk.co.ijump.jirahelper.client.domain;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by brianmason on 12/08/2016.
 */

@Entity
public class JiraIssue {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;



}

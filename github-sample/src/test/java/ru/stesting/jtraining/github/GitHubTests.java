package ru.stesting.jtraining.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by Dborisov on 14.06.2016.
 */
public class GitHubTests {

  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("374732397799ac27bba94445ed9c310739bc3572");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("Diowelcome", "java_training")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}

$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("bdd/groups.feature");
formatter.feature({
  "line": 1,
  "name": "Groups",
  "description": "",
  "id": "groups",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "line": 3,
  "name": "Group creation",
  "description": "",
  "id": "groups;group-creation",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 4,
  "name": "a set of groups",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "I create a new group with name \u003cname\u003e, header \u003cheader\u003e and footer \u003cfooter\u003e",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "the new set of groups is equal to the old set with added group",
  "keyword": "Then "
});
formatter.examples({
  "line": 8,
  "name": "",
  "description": "",
  "id": "groups;group-creation;",
  "rows": [
    {
      "cells": [
        "name",
        "header",
        "footer"
      ],
      "line": 9,
      "id": "groups;group-creation;;1"
    },
    {
      "cells": [
        "test name",
        "test header",
        "test footer"
      ],
      "line": 10,
      "id": "groups;group-creation;;2"
    },
    {
      "cells": [
        "test name1",
        "test header1",
        "test footer1"
      ],
      "line": 11,
      "id": "groups;group-creation;;3"
    },
    {
      "cells": [
        "test name2",
        "test header2",
        "test footer2"
      ],
      "line": 12,
      "id": "groups;group-creation;;4"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 5286266491,
  "status": "passed"
});
formatter.scenario({
  "line": 10,
  "name": "Group creation",
  "description": "",
  "id": "groups;group-creation;;2",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 4,
  "name": "a set of groups",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "I create a new group with name test name, header test header and footer test footer",
  "matchedColumns": [
    0,
    1,
    2
  ],
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "the new set of groups is equal to the old set with added group",
  "keyword": "Then "
});
formatter.match({
  "location": "GroupStepDefinitions.loadGroups()"
});
formatter.result({
  "duration": 524439161,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "test name",
      "offset": 31
    },
    {
      "val": "test header",
      "offset": 49
    },
    {
      "val": "test footer",
      "offset": 72
    }
  ],
  "location": "GroupStepDefinitions.createGroup(String,String,String)"
});
formatter.result({
  "duration": 2235191077,
  "status": "passed"
});
formatter.match({
  "location": "GroupStepDefinitions.verufyGroupCreated()"
});
formatter.result({
  "duration": 33254828,
  "status": "passed"
});
formatter.after({
  "duration": 859071236,
  "status": "passed"
});
formatter.before({
  "duration": 2534283381,
  "status": "passed"
});
formatter.scenario({
  "line": 11,
  "name": "Group creation",
  "description": "",
  "id": "groups;group-creation;;3",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 4,
  "name": "a set of groups",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "I create a new group with name test name1, header test header1 and footer test footer1",
  "matchedColumns": [
    0,
    1,
    2
  ],
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "the new set of groups is equal to the old set with added group",
  "keyword": "Then "
});
formatter.match({
  "location": "GroupStepDefinitions.loadGroups()"
});
formatter.result({
  "duration": 18827996,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "test name1",
      "offset": 31
    },
    {
      "val": "test header1",
      "offset": 50
    },
    {
      "val": "test footer1",
      "offset": 74
    }
  ],
  "location": "GroupStepDefinitions.createGroup(String,String,String)"
});
formatter.result({
  "duration": 2250381474,
  "status": "passed"
});
formatter.match({
  "location": "GroupStepDefinitions.verufyGroupCreated()"
});
formatter.result({
  "duration": 29239963,
  "status": "passed"
});
formatter.after({
  "duration": 757936031,
  "status": "passed"
});
formatter.before({
  "duration": 2555665209,
  "status": "passed"
});
formatter.scenario({
  "line": 12,
  "name": "Group creation",
  "description": "",
  "id": "groups;group-creation;;4",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 4,
  "name": "a set of groups",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "I create a new group with name test name2, header test header2 and footer test footer2",
  "matchedColumns": [
    0,
    1,
    2
  ],
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "the new set of groups is equal to the old set with added group",
  "keyword": "Then "
});
formatter.match({
  "location": "GroupStepDefinitions.loadGroups()"
});
formatter.result({
  "duration": 21828882,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "test name2",
      "offset": 31
    },
    {
      "val": "test header2",
      "offset": 50
    },
    {
      "val": "test footer2",
      "offset": 74
    }
  ],
  "location": "GroupStepDefinitions.createGroup(String,String,String)"
});
formatter.result({
  "duration": 2234373737,
  "status": "passed"
});
formatter.match({
  "location": "GroupStepDefinitions.verufyGroupCreated()"
});
formatter.result({
  "duration": 22523889,
  "status": "passed"
});
formatter.after({
  "duration": 710900525,
  "status": "passed"
});
});
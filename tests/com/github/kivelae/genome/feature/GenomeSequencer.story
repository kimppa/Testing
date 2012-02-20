Scenario: Identify simple sequence from simple genome

Given I have a genome <genome> 
And virus sequences are:
| sequence |
| AAA      |
| GGG      |
| CCC      |
When I run sequencer
Then found viruses are:
| virus | occurrences |
| AAA   | 1           |
| GGG   | 0           |
| CCC   | 2           |

Examples:
| genome        |
| AAACCCGGTCCCT |
| ATCGAAATCCCCG |


Scenario: Calculate sequence occurrences

Given I have a genome AAATCGGACACAAAAT
And virus sequences are:
| sequence |
| AAA      |
| ATC      |
| AAATCA   |
When I run sequencer
Then found viruses are:
| virus  | occurrences |
| AAA    | 3           |
| ATC    | 1           |
| AAATCA | 0           |


Scenario: Calculate score for virus sequences

Given I have sequences:
| sequence  | occurrences |
| AAA       | 5           |
| TTTCCC    | 4           |
| GGGCCCAAA | 10          |
When I run score calculation from context
Then scores per viruses are:
| virus      | score |
| AAA        | 30    |
| TTTCCC     | 40    |
| GGGCCCAAA  | 320   |
And total score is 390


Scenario: Score calculation from genome

Given I have a genome TAAAAGCCCGTACATGAAATGGCCCGTACAGCCCGTACAGCCCGTACA
And virus sequences are:
| sequence  |
| AAA       |
| ATG       |
| CCCGGG    |
| GCCCGTACA |
When I run sequencer
And I run score calculation
Then total score is 90
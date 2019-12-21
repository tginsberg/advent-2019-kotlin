## Advent of Code 2019 Solutions in Kotlin

[![license](https://img.shields.io/github/license/tginsberg/advent-2019-kotlin)]()

This repo is my personal attempt at solving the [Advent of Code 2019](http://adventofcode.com/2019) set of problems with the Kotlin programming language.

I am trying to solve these on the day they are posted with clear, idiomatic solutions. That means in some cases I will sacrifice performance for a more clear solution. Unlike [2017](https://github.com/tginsberg/advent-2017-kotlin) and [2018](https://github.com/tginsberg/advent-2018-kotlin), I have some travel commitments in December, so while I will endeavour to have these done day-of I can't promise it. We'll see how it goes! :)

Past years, also in Kotlin:
 * 2017 - [GitHub](https://github.com/tginsberg/advent-2017-kotlin/) and [Blog Posts](https://todd.ginsberg.com/post/advent-of-code/2017/)
 * 2018 - [GitHub](https://github.com/tginsberg/advent-2018-kotlin/) and [Blog Posts](https://todd.ginsberg.com/post/advent-of-code/2018/)


#### Daily Solution Index for 2019
|   Day   | Title                                         |  Links                                       |
| --------|-----------------------------------------------|--------------------------------------------- |
|    1    | The Tyranny of the Rocket Equation            | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2019/day1/) [\[Code\]](https://github.com/tginsberg/advent-2019-kotlin/blob/master/src/main/kotlin/com/ginsberg/advent2019/Day01.kt) [\[AoC\]](http://adventofcode.com/2019/day/1) |
|    2    | 1202 Program Alarm                            | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2019/day2/) [\[Code\]](https://github.com/tginsberg/advent-2019-kotlin/blob/master/src/main/kotlin/com/ginsberg/advent2019/Day02.kt) [\[AoC\]](http://adventofcode.com/2019/day/2) |
|    2    | 1202 Program Alarm - IntCodeComputer Version  | [\[Code\]](https://github.com/tginsberg/advent-2019-kotlin/blob/master/src/main/kotlin/com/ginsberg/advent2019/Day02IntCode.kt) |
|    3    | Crossed Wires                                 | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2019/day3/) [\[Code\]](https://github.com/tginsberg/advent-2019-kotlin/blob/master/src/main/kotlin/com/ginsberg/advent2019/Day03.kt) [\[AoC\]](http://adventofcode.com/2019/day/3) |
|    4    | Secure Container                              | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2019/day4/) [\[Code\]](https://github.com/tginsberg/advent-2019-kotlin/blob/master/src/main/kotlin/com/ginsberg/advent2019/Day04.kt) [\[AoC\]](http://adventofcode.com/2019/day/4) |
|    5    | Sunny with a Chance of Asteroids              | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2019/day5/) [\[Code\]](https://github.com/tginsberg/advent-2019-kotlin/blob/master/src/main/kotlin/com/ginsberg/advent2019/Day05.kt) [\[AoC\]](http://adventofcode.com/2019/day/5) |
|    6    | Universal Orbit Map                           | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2019/day6/) [\[Code\]](https://github.com/tginsberg/advent-2019-kotlin/blob/master/src/main/kotlin/com/ginsberg/advent2019/Day06.kt) [\[AoC\]](http://adventofcode.com/2019/day/6) |
|    7    | Amplification Circuit                         | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2019/day7/) [\[Code\]](https://github.com/tginsberg/advent-2019-kotlin/blob/master/src/main/kotlin/com/ginsberg/advent2019/Day07.kt) [\[AoC\]](http://adventofcode.com/2019/day/7) |
|    8    | Space Image Format                            | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2019/day8/) [\[Code\]](https://github.com/tginsberg/advent-2019-kotlin/blob/master/src/main/kotlin/com/ginsberg/advent2019/Day08.kt) [\[AoC\]](http://adventofcode.com/2019/day/8) |
|    9    | Sensor Boost                                  | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2019/day9/) [\[Code\]](https://github.com/tginsberg/advent-2019-kotlin/blob/master/src/main/kotlin/com/ginsberg/advent2019/Day09.kt) [\[AoC\]](http://adventofcode.com/2019/day/9) |
|    10   | Monitoring Station                            | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2019/day10/) [\[Code\]](https://github.com/tginsberg/advent-2019-kotlin/blob/master/src/main/kotlin/com/ginsberg/advent2019/Day10.kt) [\[AoC\]](http://adventofcode.com/2019/day/10) |
|    11   | Space Police                                  | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2019/day11/) [\[Code\]](https://github.com/tginsberg/advent-2019-kotlin/blob/master/src/main/kotlin/com/ginsberg/advent2019/Day11.kt) [\[AoC\]](http://adventofcode.com/2019/day/11) |
|    12   | The N-Body Problem                            | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2019/day12/) [\[Code\]](https://github.com/tginsberg/advent-2019-kotlin/blob/master/src/main/kotlin/com/ginsberg/advent2019/Day12.kt) [\[AoC\]](http://adventofcode.com/2019/day/12) |
|    13   | Care Package                                  | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2019/day13/) [\[Code\]](https://github.com/tginsberg/advent-2019-kotlin/blob/master/src/main/kotlin/com/ginsberg/advent2019/Day13.kt) [\[AoC\]](http://adventofcode.com/2019/day/13) |
|    14   | Space Stoichiometry                           | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2019/day14/) [\[Code\]](https://github.com/tginsberg/advent-2019-kotlin/blob/master/src/main/kotlin/com/ginsberg/advent2019/Day14.kt) [\[AoC\]](http://adventofcode.com/2019/day/14) |
|    15   | Oxygen System                                 | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2019/day15/)  [\[Code\]](https://github.com/tginsberg/advent-2019-kotlin/blob/master/src/main/kotlin/com/ginsberg/advent2019/Day15.kt) [\[AoC\]](http://adventofcode.com/2019/day/15) |
|    16   | Coming Soon (was traveling)                   |  |
|    17   | Coming Soon (was traveling)                   |  |
|    18   | Coming Soon (was traveling)                   |  |
|    19   | Tractor Beam                                  | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2019/day19/) [\[Code\]](https://github.com/tginsberg/advent-2019-kotlin/blob/master/src/main/kotlin/com/ginsberg/advent2019/Day19.kt) [\[AoC\]](http://adventofcode.com/2019/day/19) |
         

Copyright &copy; 2019 by Todd Ginsberg. 

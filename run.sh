#!/bin/bash
if [[ "$1" == all ]]; then
  for dir in ./src/*; do
    for f in ./src/2020/*.kts; do
      YEAR=${f:6:4}
      DAY=${f:14:2}
      TASK=$(cut -d'_' -f 2 <<<"${f}")
      TASK=${TASK:0:1}
      DAY=$(sed 's/_//g' <<<"${DAY}")
      echo ${DAY}, Day ${DAY}, Task ${TASK}
      output=$(kscript ${f})
      echo -e "Answer: \x1B[1;31m${output}\x1B[0m"
    done
  done
else
  if [[ -z "$1" ]]; then
    read YEAR <<<$(date +'%Y')
  else
    YEAR=${1}
  fi
  if [[ -z "$2" ]]; then
    read DAY <<<$(date +'%-d')
  else
    DAY=${2}
  fi
  if [[ -z "$3" ]]; then
    echo ${YEAR}, Day ${DAY}, Task 1
    output=$(kscript src/${YEAR}/Day${DAY}_1.kts)
    echo -e "Answer: \x1B[1;31m${output}\x1B[0m"
    echo ${YEAR}, Day ${DAY}, Task 2
    output=$(kscript src/${YEAR}/Day${DAY}_2.kts)
    echo -e "Answer: \x1B[1;31m${output}\x1B[0m"
  else
    echo ${YEAR}, Day ${DAY}, Task ${3}
    output=$(kscript src/${YEAR}/Day${DAY}_${3}.kts)
    echo -e "Answer: \x1B[1;31m${output}\x1B[0m"
  fi
fi
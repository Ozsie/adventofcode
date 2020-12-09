Oscars Advent of Code
------------
Using Kotlin Script!

**2020**
<span style="color:gold">\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*</span>

Running Kotlin Scripts
------------
I recommend using [sdkman](http://sdkman.io/install) to install Kotlin and kscript:
```bash
curl -s "https://get.sdkman.io" | bash     # install sdkman
source "$HOME/.sdkman/bin/sdkman-init.sh"  # add sdkman to PATH

sdk install kotlin                         # install Kotlin
```

Once Kotlin is ready, you can install kscript with
```bash
sdk install kscript
```

Running My Solutions
------------
When Kotlin and kscript are installed:`
To run all scripts
```bash
./run.sh all
```
To run todays script
```bash
./run.sh
```
To run a specific script
```bash
./run.sh YYYY DD T
```
Where YYYY is the year, DD, is the day and T is task, for example
```bash
./run.sh 2020 1 1
```

!#/usr/bin/bash

key="be5f28cd8fe54a1eabc11a2c98540a1b"
dir="$(realpath $(dirname $0)/../docs)"

for model in site line stop jour; do
    echo curl "https://api.sl.se/api2/LineData.json?model=$model&key=$key&DefaultTransportModeCode=BUS" > $dir/$model.json
done

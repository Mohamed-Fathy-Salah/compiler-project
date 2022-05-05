rm examples/blocks/*
rm examples/html/*
rm examples/intermediate/*

echo -en "enter example number : "
read Example
for (( i = 0; i < 4; i++ )); do
    java -jar out/artifacts/project_jar/project.jar "$Example" "$i"
    sleep .1
done
firefox examples/html/Example"$Example".html
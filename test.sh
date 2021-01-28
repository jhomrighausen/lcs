curl -X POST -H "Content-Type: application/json" -d '{}' http://localhost:8080/lcs 
echo '\n'
curl -X POST -H "Content-Type: application/json" -d '{"setOfStrings":[]}' http://localhost:8080/lcs 
echo '\n'
curl -X POST -H "Content-Type: application/json" -d '{"setOfStrings":[{"value":"asdfg"}]}' http://localhost:8080/lcs
echo '\n'
curl -X POST -H "Content-Type: application/json" -d '{"setOfStrings":[{"value":"dog"},{"value":"doggedly"},{"value":"dog"}]}' http://localhost:8080/lcs 
echo '\n'
curl -X POST -H "Content-Type: application/json" -d '{"setOfStrings":[{"value":"asdfg"},{"value":"gfdsa"}]}' http://localhost:8080/lcs 
echo '\n'
curl -X POST -H "Content-Type: application/json" -d '{"setOfStrings":[{"value":"comcast"},{"value":"comcastic"},{"value":"broadcast"}]}' http://localhost:8080/lcs 

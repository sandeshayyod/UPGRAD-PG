echo "read two nos"
read a
read b

echo "enter choice of operation"
echo "1. Addition"
echo "2. Substraction"
echo "3. Multiplication"
echo "4. Division"
read choice

case $choice in
	1) res=$(expr $a + $b );; 
	2) res=$(expr $a - $b );;
	3) res=$(expr $a \* $b );;
	4) res=$(expr $a / $b );;
	*) echo "invalid choice" ;;
esac
echo "result is : $res"

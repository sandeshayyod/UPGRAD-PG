read a
if [ $a -eq 0 ];then
	echo "number entered is zero"
elif [ $(( $a % 2)) -eq 0 ];then
	echo "number entered is even"
else 
	echo "number entered is odd"
fi

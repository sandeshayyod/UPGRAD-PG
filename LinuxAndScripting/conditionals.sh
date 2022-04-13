
echo "Enter a"
read a
echo "Enter b"
read b
if [ $a -gt $b ];then
	echo "greater"
elif [ $a -lt $b ]; then
	echo "lesser"
else
	echo "equal"
fi

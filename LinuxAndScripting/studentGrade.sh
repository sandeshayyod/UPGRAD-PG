read m
if [ $a > 100 ];then
	echo "The grade of student named Ram is A"
elif [ $a > 80 ];then
	echo "The grade of student named Ram is B"
elif [ $a > 40 ]; then
	echo "The grade of student named Ram is C"
else 
	echo "The grade of student named Ram is D"
fi

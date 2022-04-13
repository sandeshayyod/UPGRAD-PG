read age
read candidate
if [ $age -lt 18 ];then 
	echo "You are not eligible"
else 
	case $candidate in
		1) echo "You have voted for Ram";;
		2) echo "You have voted for Shyam";;
		3) echo "You have voted for Ghanshyam";;
	esac
fi

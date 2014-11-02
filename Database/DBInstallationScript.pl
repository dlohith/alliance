#
#	Script to create the DB and schema
#

use Cwd qw();

unlink("schemaCreation.log");
unlink("./createDB.txt");
unlink("./createDBSchema.txt");

my ($line, $userName,$dbName,$mysqlloc);
#	Read the username and database name to create a database
open (MYFILE, 'database.props');
while($line=<MYFILE>){
	chomp($line);
	my @values = split('=', $line);
	if($line=~/dbuser/){
		$userName=$values[1];	
	}
	if($line=~/dbname/){
		$dbName=$values[1];
	}
	if($line=~/mysqllocation/){
		$mysqlloc=$values[1];
	}
	
}
close(MYFILE);

my $loc = Cwd::cwd();

#       Creates a file for creating Database
open (OUTFILE, '>createDB.txt');
print OUTFILE "create database if not exists $dbName;\n";
print OUTFILE "grant all privileges on $dbName.* to '".$userName."'\@'localhost';\n";
close(OUTFILE);


#	Creates a file for creating tables
open (OUTFILE, '>>createDBSchema.txt');
open (MYFILE, 'tablesFlow.txt');
while($line=<MYFILE>){
	chomp($line);
	$line = "source ".$loc."/Tables/".$line."\n";
	print OUTFILE $line;
}
close(MYFILE);

#	Creates a file for creating stored procedures
@files = <./StoredProcedure/*>;

foreach $file (@files) {
  print  OUTFILE "source ".$loc."/".$file . "\n";
}
close(OUTFILE);

print "Creating Database : $dbName schema\n";
system("$mysqlloc -u $userName -p < ./createDB.txt >> ./schemaCreation.log 2>&1"); 
system("$mysqlloc -u $userName -p $dbName < ./createDBSchema.txt >> ./schemaCreation.log 2>&1"); 
print "Creating tables on the database : $dbName\n";


unlink("./createDB.txt");
unlink("./createDBSchema.txt");


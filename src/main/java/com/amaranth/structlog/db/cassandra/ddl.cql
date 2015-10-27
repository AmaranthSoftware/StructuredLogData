CREATE TYPE IF NOT EXISTS NestedStructLog (
	id text,
	startTimestamp timeuuid,
	endTimestamp timeuuid,
	exceptionsCaught set<text>,
	attributes Map<text, text>,
	input text,
	output text,
	isRoot boolean,
	componentName text,
	userId text,
	colo text,
	machine text,
	serviceCallStackId text,
	siteId text
);

create table IF NOT EXISTS RootStructLog (
	id text,
	startTimestamp timeuuid,
	endTimestamp timeuuid,
	exceptionsCaught set<text>,
	dependentStructLog set<frozen<NestedStructLog>>,
	attributes Map<text, text>,
	input text,
	output text,
	isRoot boolean,
	componentName text,
	userId text,
	colo text,
	machine text,
	serviceCallStackId text,
	siteId text,
	PRIMARY KEY (( userId, componentName ))
);
#include <stdio.h>
#include <string.h>

int main(int argc, char *argv[]) {

	char text[1024];
	scanf("%s", &text[0]);

	int i;
	for(i = 0; i < strlen(text); i++) {
		text[i] = toupper(text[i]);
	}

	printf("%s\n", text);
	return 0;
}


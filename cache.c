/*
 *  cache total size is 32 bytes
 * */

//Zach Holst zholst@uccs.edu
#include<stdio.h>
#include<math.h>
#include<assert.h>
#include<stdlib.h>
#include "cache.h"
#include "memory.h"

/* 
 * configure following parameters of the cache in question 2
 *
 * */

#define S 1
#define B 8
#define E 4

//S is number of sets
//B is size of block
//E is Lines per set

/* define the struct of the cache 
 * the Line struct below is given as the building brick
 * */
 
typedef struct {
    int valid;
    unsigned short tag;
    unsigned char block[B];
} line;

typedef struct {
	line *lines;
} set;

typedef struct {
	 set *sets;
} cache;


/* global variable C to denote the cache */
static int is_init = 0;
// static struct Cache C;
static cache C;

/*
* this method initiates the cache
*/
void init_cache() {
    assert(B*E*S == 32);
    /* to initialize the cache */
    
    set aSet;
	line aLine;
	int setIndex;
	int lineIndex;
	C.sets = (set *) malloc(sizeof(set) * S);

	for (setIndex = 0; setIndex < S; setIndex ++) {
		aSet.lines =  (line *) malloc(sizeof(line) * E);
		C.sets[setIndex] = aSet;

		for (lineIndex = 0; lineIndex < E; lineIndex ++) {
			aLine.valid = 0;
			aLine.tag = 0; 
			aSet.lines[lineIndex] = aLine;	
		}	
	}
}

/*
* This method prints the cache block
*/
void print_line(line l) {
	int j;
    printf("Valid:%d; tag:", l.valid);
    printf("%x", l.tag);

    printf("; block:");
    for( j = 0; j < B; j++) {
        printf("%02x", l.block[j]);
    }
    printf("\n");
}

/*
* This method searches the cache to find a cache hit or miss
*/
int search_cache(unsigned short addr, unsigned char *ptr) {
    /* search the cache to find a cache hit or end up with miss */
    //Miss = 0, hit = 1
    //Searchs for tag
    int i, j;
    int is_hit = 0;
    
		for( i = 0; i < S; i++) {
      for( j = 0; j < E; j++){
      	//if valid
        if(C.sets[i].lines[j].valid == 1) {
        	//if tag equals address
          if(C.sets[i].lines[j].tag == addr) {
            *ptr = C.sets[i].lines[j].block[0];
            is_hit = 1;
          }
        }
      }
    } 
    return is_hit;
}

/*
* this method reads a block from memory and stores into the cache
*/
void fill_cache(unsigned short addr) {
    /* read a block from memory and store it in the cache */  
    //If an empty spot exists
	int i,j,k;

    for( i = 0; i < S; i++){
    	for( j = 0; j < E; j++){
    		if(C.sets[i].lines[j].tag == 0){
    			C.sets[i].lines[j].tag = addr;
    			C.sets[i].lines[j].valid = 1;
    			read_memory(addr, C.sets[i].lines[j].block, B);
			}
    	}
	} 
	
	int setEvict;	//look in set
	int lineEvict;  // to find this line to evict
	
	//New random numbers
	for( k = 0; k < 5; k++){
		setEvict = rand() % S;
		lineEvict = rand() % E;
	}

	C.sets[setEvict].lines[lineEvict].tag = addr;
	read_memory(addr, C.sets[setEvict].lines[lineEvict].block, B);	
}

/*
*This method reads the cache and returns the address
*/
unsigned char read_cache(unsigned short addr) {
    int is_hit;
    unsigned char a;

    if (is_init == 0) {
        init_cache();
        is_init = 1;
    }
    
    
    is_hit = search_cache(addr, &a);

    if (is_hit == 0) {
        fill_cache(addr);
        search_cache(addr, &a);
    }
	
    return a;
}

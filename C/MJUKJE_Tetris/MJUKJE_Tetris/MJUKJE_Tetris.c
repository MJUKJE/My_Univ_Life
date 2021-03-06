
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
#include <Windows.h>
#include <conio.h>

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//turboc.h관련 내용들
typedef enum { NOCURSOR, SOLIDCURSOR, NORMALCURSOR } CURSOR_TYPE;
void clrscr();
void gotoxy(int x, int y);
int wherex();
int wherey();
void setcursortype(CURSOR_TYPE c);
#define delay(n) Sleep(n)							

//여기까지 터보씨관련 헤더파일을 라이브러리에 넣기 번거로워서 필요한 함수를 터보씨헤더에서 떼어 왔습니다.
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//보드 설정
#define Board_Height 20 //게임진행되는 보드의 칸수 높이
#define Board_Width 10// 칸수 너비
#define BX 10 //보드의 시작좌표
#define BY 5 //보드의 시작좌표
//키보드 설정
#define UP 72
#define DOWN 80
#define RIGHT 77
#define LEFT 75
#define SPACE 32
#define ESC 27
#define ENTER 13
#define Z 90
#define z 122

#define WIN 99 //아무값이나
#define PAUSE 88

//색상설정
#define COL GetStdHandle(STD_OUTPUT_HANDLE) //미리 정의해 둔다
#define Black SetConsoleTextAttribute(COL, 0x0000); //검정색
#define Yellow SetConsoleTextAttribute(COL, 0x000e); //노랑색
#define Blue SetConsoleTextAttribute(COL, 0x0009); //파란색
#define High_Green SetConsoleTextAttribute(COL, 0x000a);//연두색
#define SkyBlue SetConsoleTextAttribute(COL, 0x000b);//하늘색
#define Red SetConsoleTextAttribute(COL, 0x000c);//빨간색
#define Plum SetConsoleTextAttribute(COL, 0x000d);//자주색
#define Gold SetConsoleTextAttribute(COL, 0x0006);//금색
#define Gray SetConsoleTextAttribute(COL, 0x0008);//회색
#define Original SetConsoleTextAttribute(COL, 0x0007);//원래색
#define High SetConsoleTextAttribute(COL,0x000d);//형광

//음계 설정
#define DO 260 //옥타브가 460씩 차이
#define RE 320
#define MI 400
#define PA 450    
#define SOL 540
#define RA 630
#define SI 720




//전역변수 선언
int board[Board_Width + 2][Board_Height + 2];//2차원 배열로 게임판 생성
int block_x;//현재위치 x
int block_y;//현재위치 y
int preview_x;//미리보기 좌표
int preview_y;
int block;//블록 종류
int spin;//블록 스핀상태
int next_block;//다음 블록종류
int clear_Score;//단계별 클리어 점수
int game_Speed;//블록의 속도 즉 게임난이도에 따른 속도
int game_Level = 1;//게임 레벨 1로 초기화
int score = 0;//현재 점수 0으로 초기화
int save_block = 7;//저장블록 처음엔 없어야 하기에 0~6을 벗어난 범위를 지정

				   //빈공간 벽 블록을 구분
enum { PREVIEW, EMPTY, BRICK, WALL };			//순서대로 0 1 2 3로 인식함
char *tile_style[] = { "□", " ","■","□" }; //0 1 2 3에 따라 나타내는것 


										   //좌표 구조체
typedef struct Point//점을 구조체로 나타냄
{
	int x, y;
}Point;

//블록 모양 즉 기준점에서 부터 표시할 좌표를 지정해줌
Point shape[7][4][4] = //[블럭 종류][회전상태][그에따른 좌표] 즉 7 4 4 로 구조체 포인트형 3차원 배열 
{
	{ { -1,0,0,0,1,0,2,0 },{ 0,2,0,1,0,0,0,-1 },{ -1,0,0,0,1,0,2,0 },{ 0,2,0,1,0,0,0,-1 } },						//길쭉이블록
{ { 0,0,-1,0,0,-1,-1,-1 },{ 0,0,-1,0,0,-1,-1,-1 },{ 0,0,-1,0,0,-1,-1,-1 },{ 0,0,-1,0,0,-1,-1,-1 } },			//네모난블록
{ { 0,0,-1,0,0,-1,1,-1 },{ 0,0,0,1,-1,0,-1,-1 },{ 0,0,-1,0,0,-1,1,-1 },{ 0,0,0,1,-1,0,-1,-1 } },				//꺾인블록 2모양
{ { 0,0,-1,-1,0,-1,1,0 },{ 0,0,-1,0,-1,1,0,-1 },{ 0,0,-1,-1,0,-1,1,0 },{ 0,0,-1,0,-1,1,0,-1 } },				//꺾인블록 s모양
{ { 0,0,-1,0,1,0,-1,-1 },{ 0,0,0,-1,0,1,1,-1 },{ 0,0,-1,0,1,0,1,1 },{ 0,0,0,-1,0,1,-1,1 } },					//ㄱ블록 역방향
{ { 0,0,1,0,-1,0,1,-1 },{ 0,0,0,-1,0,1,1,1 },{ 0,0,1,0,-1,0,-1,1 },{ 0,0,0,1,0,-1,-1,-1 } },					//ㄱ블록
{ { 0,0,-1,0,1,0,0,-1 },{ 0,0,0,-1,0,1,1,0 },{ 0,0,-1,0,1,0,0,1 },{ 0,0,-1,0,0,-1,0,1 } }						//ㅗ블록
};

//함수 원형 선언
void PrintMainScreen();					//메인 스크린 출력 함수
void Clean_Board();						//게임 보드청소
void Game_LevelSet(int select_Level);	//게임 난이도설정
void PrintGameScreen();					//게임화면 표시
void Print_NextBlock(BOOL show);		//다음 블록 표시
void Print_Block(BOOL show);			//현재블록 표시
void GameClear();						//게임 클리어 함수
void GameOver();						//게임 오버 함수
int Play_Game();						//게임 플레이 제어함수
int InputKey();							//키보드 입력키 제어함수
void TestFull();						//꽉찬줄 없애주는 함수
BOOL MoveDown();						//키 다운과 연동되는 함수 내릴곳이 있는지
void DrawBoard();						//맵을 그려주는 함수
int GetAround(int r, int x, int y);		//주변을 살피는데 쓰임
void BlockColor(int block);				//블록 색상을 지정
int SelectMenu();						//메뉴관련
void BlockSave();//블록 세이브용
void Print_SaveBlock(BOOL show);		//세이브된 블록을 출력하는 함수
void Clean_Save();						//다음 게임을 할때 세이브 블록을 청소해준다
int SelectLevel();						//레벨을 설정하는 함수




void main()
{

	while (1)							//탈출할때 까지 반복
	{
		Clean_Board();					//게임 보드 청소한번
		game_Level = 1;					//레벨 초기화
		Game_LevelSet(game_Level);		//함수에 넣어 레벨에 맞는 클리어 점수와 속도등을 초기화 해준다
		score = 0;						//다시시작할떄를 대비해 점수 초기화
		save_block = 7;

		clrscr();						//전체 화면 지우기
		setcursortype(NOCURSOR);		//커서 숨기기
		PrintMainScreen();				//메인 화면 출력
		clrscr();						//전체 화면 지우기
		PrintGameScreen();				//게임 화면 출력
		if (Play_Game() == WIN)			//만약 단계 클리어가 되면
		{
			while (1)
			{
				game_Level = (game_Level == 5) ? game_Level : game_Level + 1;	//3항 연산자에 주의 레벨 5가 아니면 +1레벨 5레벨이면 계속 유지
				Game_LevelSet(game_Level);										//그값을 함수에 넣어 레벨업 처리

				clrscr();														//전체 화면 정리
				PrintGameScreen();												//다시 게임화면 출력
				if (Play_Game() != WIN) break;									// 단계 클리어시 와일문 탈출
			}
		}

	}
}


void Game_LevelSet(int select_Level)	//레벨을 입력받으면 그에맞는 난이도를 설정해주는 함수
{
	switch (select_Level)				//현재 레벨
	{
	case 1:
		clear_Score = 10000;				//단계별 클리어점수
		game_Speed = 22;				//블록이 떨어지는 스피드 숫자가 클수록 느림
		break;
	case 2:
		clear_Score = 20000;
		game_Speed = 15;
		break;
	case 3:
		clear_Score = 40000;
		game_Speed = 10;
		break;
	case 4:
		clear_Score = 80000;
		game_Speed = 6;
		break;
	case 5:
		clear_Score = 300000;
		game_Speed = 3;
		break;
	}
}

void BlockColor(int block)																			//블록별로 색 지정해두는것
{
	if (block == 0) Red;
	if (block == 1) High_Green;
	if (block == 2) Gold;
	if (block == 3) Plum;
	if (block == 4) Blue;
	if (block == 5) SkyBlue;
	if (block == 6) Yellow;
	if (block == 9) Original;
}


void PrintMainScreen()
{
	system("mode con cols=80 lines=30");//시스템 상에서 x좌표 0~79까지 y좌표 0~29까지 창크기를 고정
	int select_Level = 1;					//골라진 메뉴 번호

											//테트리스 도형 출력
	gotoxy(8 + 10, 6);   Yellow puts("■■■");		//puts를 쓰는 이유는 \n을 그때그때 쓰기 귀찮아서이다
	gotoxy(8 + 12, 7);     Yellow puts("■");
	gotoxy(8 + 12, 8);     Yellow puts("■");
	gotoxy(8 + 12, 9);     Yellow puts("■");
	gotoxy(8 + 12, 10);    Yellow puts("■");

	gotoxy(8 + 17, 6);   High_Green puts("■■■");
	gotoxy(8 + 17, 7);   High_Green puts("■");
	gotoxy(8 + 17, 8);   High_Green puts("■■■");
	gotoxy(8 + 17, 9);   High_Green puts("■");
	gotoxy(8 + 17, 10);  High_Green puts("■■■");

	gotoxy(8 + 24, 6);   SkyBlue puts("■■■");
	gotoxy(8 + 26, 7);     SkyBlue puts("■");
	gotoxy(8 + 26, 8);     SkyBlue puts("■");
	gotoxy(8 + 26, 9);     SkyBlue puts("■");
	gotoxy(8 + 26, 10);    SkyBlue puts("■");

	gotoxy(8 + 31, 6);   Red puts("■■");
	gotoxy(8 + 31, 7);   Red puts("■  ■");
	gotoxy(8 + 31, 8);   Red puts("■■■");
	gotoxy(8 + 31, 9);   Red puts("■ ■");
	gotoxy(8 + 31, 10);  Red puts("■  ■");

	gotoxy(8 + 38, 6);   Yellow puts("■■■");
	gotoxy(8 + 40, 7);     Yellow puts("■");
	gotoxy(8 + 40, 8);     Yellow puts("■");
	gotoxy(8 + 40, 9);     Yellow puts("■");
	gotoxy(8 + 38, 10);  Yellow puts("■■■");

	gotoxy(8 + 45, 6);   High_Green puts("■■■");
	gotoxy(8 + 45, 7);   High_Green puts("■");
	gotoxy(8 + 45, 8);   High_Green puts("■■■");
	gotoxy(8 + 49, 9);   High_Green puts("■");
	gotoxy(8 + 45, 10);  High_Green puts("■■■");
	//정보 출력
	gotoxy(8 + 14, 13);  Original puts("정보통신응용프로그램 최창석 교수님");
	gotoxy(8 + 14, 14);  SkyBlue puts("    5 조   김 재 은   민 순 욱");
	//메뉴 3가지
	gotoxy(16, 18);  Gray puts("▶ ①   START GAME !");
	gotoxy(19, 20);  Gray puts("②   SET START LEVEL !");
	gotoxy(19, 22);  Gray puts("③   EXIT GAME !");
	Original;											//벽등의 색을 원래대로 돌려주는 부분



	while (1)												//제대로된 메뉴를 고를때 까지 반복
	{
		select_Level = SelectMenu();						//선택레벨로 레벨을 설정
		break;
	}
	if (select_Level == 1) Game_LevelSet(select_Level);		//1번 메뉴 시작을 선택시 레벨설정
	else if (select_Level == 2)								//2번 메뉴 레벨설정 선택시
	{
		while (1)											//제대로된 레벨 설정까지 반복
		{
			clrscr();										//전체 화면 청소
			gotoxy(8 + 11, 5);  Yellow puts("■");
			gotoxy(8 + 11, 6);  Yellow puts("■");
			gotoxy(8 + 11, 7);  Yellow puts("■");
			gotoxy(8 + 11, 8);  Yellow puts("■");
			gotoxy(8 + 11, 9);  Yellow puts("■■■");

			gotoxy(8 + 19, 5);  Yellow puts("■■■");
			gotoxy(8 + 19, 6);  Yellow puts("■");
			gotoxy(8 + 19, 7);  Yellow puts("■■■");
			gotoxy(8 + 19, 8);  Yellow puts("■");
			gotoxy(8 + 19, 9);  Yellow puts("■■■");

			gotoxy(8 + 27, 5);  Yellow puts("■  ■");
			gotoxy(8 + 27, 6);  Yellow puts("■  ■");
			gotoxy(8 + 27, 7);  Yellow puts("■  ■");
			gotoxy(8 + 27, 8);  Yellow puts(" ■■");
			gotoxy(8 + 27, 9);  Yellow puts("  ■");


			gotoxy(8 + 35, 5);  Yellow puts("■■■");
			gotoxy(8 + 35, 6);  Yellow puts("■");
			gotoxy(8 + 35, 7);  Yellow puts("■■■");
			gotoxy(8 + 35, 8);  Yellow puts("■");
			gotoxy(8 + 35, 9);  Yellow puts("■■■");

			gotoxy(8 + 43, 5);  Yellow puts("■");
			gotoxy(8 + 43, 6);  Yellow puts("■");
			gotoxy(8 + 43, 7);  Yellow puts("■");
			gotoxy(8 + 43, 8);  Yellow puts("■");
			gotoxy(8 + 43, 9);  Yellow puts("■■■");

			gotoxy(8 + 51, 5);  Yellow puts("■");
			gotoxy(8 + 51, 6);  Yellow puts("■");
			gotoxy(8 + 51, 7);  Yellow puts("■");
			gotoxy(8 + 51, 8);  Yellow puts("");
			gotoxy(8 + 51, 9);  Yellow puts("■");

			gotoxy(16, 15);  SkyBlue puts("▶ PRACTICE MODE");			//메뉴 5개 출력
			gotoxy(19, 17);  High_Green puts("EASY MODE");
			gotoxy(19, 19);  Yellow puts("NORMAL MODE");
			gotoxy(19, 21);  Plum puts("HARD MODE");
			gotoxy(19, 23);  Red puts("HELL MODE");

			select_Level = SelectLevel();							//골라진 레벨을 변수에 대입
			game_Level = select_Level;								//변수를 게임 레벨에 대입

			Game_LevelSet(select_Level);							//레벨함수에 넣고 적용하기
			if (select_Level > 0 && select_Level < 6) break;		//범위내의 레벨을 고를시 와일문 탈출
		}
	}
	else {
		clrscr();
		gotoxy(8 + 15, 11);   Yellow puts("■■");
		gotoxy(8 + 15, 12);  Yellow puts("■  ■");
		gotoxy(8 + 15, 13);  Yellow puts("■■■");
		gotoxy(8 + 15, 14);  Yellow puts("■  ■");
		gotoxy(8 + 15, 15);  Yellow puts("■■■");

		gotoxy(8 + 25, 11);   Yellow puts("■  ■");
		gotoxy(8 + 25, 12);  Yellow puts("■  ■");
		gotoxy(8 + 25, 13);  Yellow puts(" ■■");
		gotoxy(8 + 25, 14);  Yellow puts("  ■");
		gotoxy(8 + 25, 15);  Yellow puts("  ■");

		gotoxy(8 + 35, 11);  Yellow puts("■■■");
		gotoxy(8 + 35, 12);  Yellow puts("■");
		gotoxy(8 + 35, 13);  Yellow puts("■■■");
		gotoxy(8 + 35, 14);  Yellow puts("■");
		gotoxy(8 + 35, 15);  Yellow puts("■■■");

		gotoxy(8 + 45, 11); Red puts("");
		gotoxy(8 + 45, 12); Red puts("  ■    ■");
		gotoxy(8 + 45, 13); Red puts("");
		gotoxy(8 + 45, 14); Red puts("■        ■");
		gotoxy(8 + 45, 15); Red puts("  ■■■■");
		Black														//아무키나 누르라는말 없애기용
			exit(0);

	}//3번 나가기 설정시 게임 종료
	Original
}

void Clean_Board()													//게임 보드 청소 즉 보드구성요소 배열의 값을 초기화
{
	int x, y;
	for (x = 0; x < Board_Width + 2; x++)							//게임보드부분의 x좌표 하나당 모든 y좌표를 대입하기 위해 for문을 사용
	{
		for (y = 0; y < Board_Height + 2; y++)						//너비와 높이를 넘지않게 컨트롤
		{
			board[x][y] = (x == 0 || y == 0 || x == Board_Width + 1 || y == Board_Height + 1 ? WALL : EMPTY); //벽부분은 벽을 출력 아닌곳은 빈곳으로 둔다
		}
	}
}


void PrintGameScreen()												//게임 화면 출력
{
	system("mode con cols=80 lines=30");//시스템 상에서 x좌표 0~79까지 y좌표 0~29까지 창크기를 고정
	int x, y;
	for (x = 0; x < Board_Width + 2; x++)
	{
		for (y = 0; y < Board_Height + 2; y++)
		{
			gotoxy(x * 2 + BX, y + BY);								//해당 좌표로 이동해서
			puts(tile_style[board[x][y]]);							//현재 보드의 상황에 따라서 구성블록을 출력해준다
		}
	}

	//화면 구성
	gotoxy((BX * 2) + 16, 5);  Blue puts("  정보통신응용  프로그램  ");								//puts앞은 미리 정의해둔 색상을 의미함
	gotoxy((BX * 2) + 16, 6);  SkyBlue puts("     최창석   교수님      ");
	gotoxy((BX * 2) + 16, 7);  Original printf("   %d 단계   ▣ 점수 : %d\n", game_Level, score);	//puts로는 변수 출력이 힘드므로 printf를 사용한다
	gotoxy((BX * 2) + 16, 8);  Original puts("▣▣▣▣▣▣▣▣▣▣▣▣▣");
	gotoxy((BX * 2) + 16, 9);  Original puts("▣          ▣          ▣");
	gotoxy((BX * 2) + 16, 10); Original puts("▣          ▣          ▣");
	gotoxy((BX * 2) + 16, 11); Original puts("▣          ▣          ▣");
	gotoxy((BX * 2) + 16, 12); Original puts("▣          ▣          ▣");
	gotoxy((BX * 2) + 16, 13); Original puts("▣▣▣▣▣▣▣▣▣▣▣▣▣");
	gotoxy((BX * 2) + 16, 15); Red puts("            5 조          ");
	gotoxy((BX * 2) + 16, 16); SkyBlue puts("    김 재 은   민 순 욱   ");
	gotoxy((BX * 2) + 16, 18); Original puts("▣▣▣▣ 게임방법 ▣▣▣▣");
	gotoxy((BX * 2) + 16, 19); Original puts("▣  ↑   : 블럭 회전    ▣");
	gotoxy((BX * 2) + 16, 20); Original puts("▣ ←→  : 좌우 이동    ▣");
	gotoxy((BX * 2) + 16, 21); Original puts("▣  ↓   : 블럭 내리기  ▣");
	gotoxy((BX * 2) + 16, 22); Original puts("▣ space : 즉시 내리기  ▣");
	gotoxy((BX * 2) + 16, 23); Original puts("▣   Z   : 블럭 저장    ▣");
	gotoxy((BX * 2) + 16, 24); Original puts("▣ PAUSE : 일시 정지    ▣");
	gotoxy((BX * 2) + 16, 25); Original puts("▣ E S C : 나 가 기     ▣");
	gotoxy((BX * 2) + 16, 26); Original puts("▣▣▣▣▣▣▣▣▣▣▣▣▣");
}


void DrawBoard()													//보드를 그린다
{
	int x, y;
	for (x = 1; x < Board_Width + 1; x++)
	{
		for (y = 1; y < Board_Height + 1; y++)
		{
			gotoxy(BX + x * 2, BY + y);
			Original puts(tile_style[board[x][y]]);					//원래 색상으로 보드를 출력한다
		}
	}
	gotoxy((BX * 2) + 16, 7);  Original printf("   %d 단계   ▣ 점수 : %d\n", game_Level, score);	//원래 색상으로 점수와 레벨을 출력한다
}

BOOL MoveDown()														//아래로 내려가는 걸 담당해주는 함수
{
	int i;

	if (GetAround(spin, block_x, block_y + 1) == EMPTY || PREVIEW)	//현재좌표의 아래가 비어있거나 미리보기 블록일 경우에
	{
		Print_Block(FALSE);											//블록을 잠시 지운뒤
		block_y++;													//한칸내린뒤
		BlockColor(block); Print_Block(TRUE);											//블록을 출력한다
		return TRUE;												//트루값을 리턴해준다
	}
	else
	{
		for (i = 0; i < 4; i++)
		{
			board[block_x + shape[block][spin][i].x][block_y + shape[block][spin][i].y] = BRICK;	//그자리에 블록을 출력한다
		}
		Print_NextBlock(FALSE);																	//다음 블록을 안보이게 한다
		TestFull();																				//한줄이 꽉찼는지 확인
		Beep(DO, 20);																			//효과음
		return FALSE;
	}
}


int GetAround(int r, int x, int y)																	//어디론가 옮긴 즉 한칸이동한 블록의 위치에 뭔가 있는지 판단함
{
	int i, k = EMPTY;																				//k를 빈공간, enum으로 인해 값으로는 1로 초기화해둠
	for (i = 0; i < 4; i++)
	{
		k = max(k, board[shape[block][r][i].x + x][shape[block][r][i].y + y]);						//맥스는 둘중 큰걸 반환 즉 프리뷰나 빈공간이 아니면 K값이 바뀐다 미리보기는 0의값 빈공간은 1이라 미리보기는 인식을 못함
																									//enum으로 미리보기와 빈칸이 0과 1 블록과 벽은 2와 3으로 값을 지정햇기에 지정한 k의값 즉 1이 변하려면 주변에 벽이나 블록이 있어야 한다 
		if (k != EMPTY || PREVIEW) break;															//비거나 미리보기가 아니면 탈출
	}
	return k;				//k의 값을 리턴해 준다 이함수는 주변에 벽이나 블록이 있는지 판단하는데 쓰인다 if(GetAround(r,x,y)==EMPTY || PREVIEW)이렇게 쓰면 
							//k값이 0이거나 1일때만 이프문이 발동 즉 벽이거나 블록이 주변에 있으면 이프문을 발동하지 않는 것이다
}



void TestFull()																						//줄이 가득차있는지 판별
{
	int x, y;
	int a;																							//y값 임시저장할 변수

	for (y = 1; y < Board_Height + 1; y++)
	{
		for (x = 1; x < Board_Width + 1; x++)
		{
			if (board[x][y] != BRICK) break;														//한줄이 모두 블록이 아니면 즉 빈곳이 있으면 탈출
		}
		if (x == Board_Width + 1)																	//한줄의 끝까지 모두 블럭일때 즉 위 포문이 끝까지 돌면 여기로 넘어옴		
		{
			for (a = y; a > 1; a--)																	//a에 y값을 대입하고 1이 될떄 까지
			{																						//즉 꽉찬줄 위쪽을 모두
				for (x = 1; x < Board_Width + 1; x++)
				{
					board[x][a] = board[x][a - 1];													//아랫줄에 한칸위의 줄요소를 그린다 즉 한칸씩 내린다
				}
			}

			score += 1000;																			//줄하나당 점수 1000점 업
			DrawBoard();																			//다시설정된 보드를 그림
			Beep(DO + 262, 20);																		//효과음
			Beep(MI + 262, 20);
			Beep(RE + 262 + 460, 30);
			delay(50);
		}
	}
}

int InputKey()																						//키가 눌리는것들을 다 처리해주는 함수
{
	int ch;																							//눌린키를 저장할 변수 아스키코드값으로 저장된다
	int temp_Spin;																					//임시  스핀값을 저장하는 변수

	if (_kbhit())																					//키보드가 눌린 유무를 판단함
	{																								//눌리면 1 아니면 0을 반환함
		ch = _getch();																				//눌린키를 바로 ch 변수에 저장한다
																									//방향키등 키보드 입력
		if (ch == 0xE0 || ch == 0)																	//문자키가 아닌 확장키가 입력되었을 경우에!
		{
			ch = _getch();																			//눌린키를 변수에 저장
			switch (ch)
			{
			case UP:																			//위 방향키 일 경우
			{
				temp_Spin = (spin == 3) ? 0 : spin + 1;											//회전값이 4번쨰 즉 3이면 0으로 돌아간다
				if (GetAround(temp_Spin, block_x, block_y) == EMPTY || PREVIEW)					//회전에 걸리적거리는걸 확인
				{
					Print_Block(FALSE);															//잠시 지운뒤
					spin = temp_Spin;															//임시 회전값을 진짜 회전값에 대입
					BlockColor(block); Print_Block(TRUE);															//출력해버리는 것
				}
				break;
			}
			case RIGHT:																			//오른쪽 방향키
			{
				if (GetAround(spin, block_x + 1, block_y) == EMPTY || PREVIEW)					//오른쪽이 빈칸이거나 미리보기 일경우
				{
					Print_Block(FALSE);															//잠시 지운뒤
					block_x++;																	//오른쪽으로 이동
					BlockColor(block); Print_Block(TRUE);															//출력해 준다
				}
				break;
			}
			case DOWN:																			//아래쪽 방향키
			{
				if (MoveDown() != TRUE)															//무브다운 함수가 참을 반환하지 않으면 즉 어딘가에 닿았을때
				{
					return TRUE;																//참을 반환
				}
				break;
			}
			case LEFT:																			//왼쪽 방향키
			{
				if (GetAround(spin, block_x - 1, block_y) == EMPTY || PREVIEW)					//왼쪽이 빈칸이거나 미리보기 일경우
				{
					Print_Block(FALSE);															//지우고
					block_x--;																	//왼쪽 한칸이동
					BlockColor(block); Print_Block(TRUE);															//출력
				}
				break;
			}
			}

		}
		else if (ch == ' ')																			//SPACE바 입력시
		{
			while (MoveDown())																		//무브다운을 무한 반복
			{
			}
			return TRUE;																			//참을 반환
		}
		else if (ch == 'p' || ch == 'P')															//p나 P가 입력 되었을때
		{
			clrscr();																				//화면 청소
			gotoxy(15, 10);																			//일시정지 문구 출력할 좌표로 이동
			Gray puts("잠시 중지...다시 시작하려면 아무키나 누르세요.");							//문구 출력
			_getch();																				//어떤키든 입력되면
			clrscr();																				//화면 청소
			PrintGameScreen();																		//게임화면 출력
			DrawBoard();																			//보드 그리기
			BlockColor(block); Print_Block(TRUE);													//블록 출력
		}
		else if (ch == ESC)																			//이에스씨입력시
		{
			return ESC;																				//이에스시를 반환
		}
		else if (ch == z)																			//z를 입력시
		{
			BlockSave();											//세이브용 함수
		}
		else {}																						//나머진 무시
	}
	return FALSE;																					//거짓을 반환
}

int Play_Game()																						//게임 컨트롤 함수 여기 어려움
{

	int	nFrame = game_Speed;																		//프레임값을 게임속도로 초기화
	int nStay;																						//여기에 프레임값을 넣고 하나씩 줄여가는것
	int	StopCmd = 0;																				//WIN이나 ESC값을 담는 변수//값이 담기면 멈춤

	srand((unsigned)time(0));																		//난수부분

	block = rand() % 7;																				//처음에만 블록 난수로 정하기

	while (1)
	{
		block_x = Board_Width / 2;																	//가운데서 나오게 하는 것
		block_y = 3;

		next_block = rand() % 7;																	//다음 블록 난수로 정하기
		spin = 0;																					//다음블록의 회전상태
		nStay = nFrame;																				//기다릴 시간을 대입


		BlockColor(block); Print_Block(TRUE);														//첫블록 출현 블록종류에 맞는 색상
		BlockColor(next_block); Print_NextBlock(TRUE);												//다음 블록 표시
		BlockColor(save_block); Print_SaveBlock(TRUE);												//저장 블록 표시

		if (GetAround(spin, block_x, block_y) != EMPTY || PREVIEW) break;							//빈공간으로만 움직일수 있게 설정 즉 비지않으면 탈출

		while (1)
		{
			if (--nStay == 0)																		//기다리는 시간이 다되면
			{
				nStay = nFrame;																		//다시한번 기다리는 시간 초기화
				if (MoveDown() == FALSE) break;														//블럭이 바닥에 닿으면 와일문 탈출
			}

			StopCmd = (score >= clear_Score) ? WIN : InputKey();									//점수 넘으면 클리어 점수미달이면 다시 키입력 받는다
			if (StopCmd != FALSE) break;															//만약 스탑커맨드에 값이 있으면 탈출

			delay(50);
		}
		if (StopCmd == ESC || StopCmd == WIN) break;												//이기거나 ESC면 탈출

		block = next_block;																			//보여주던 다음블록을 현재블록에 대입
	}

	if (StopCmd == WIN)																				//클리어시 클리어 함수 불러옴
	{
		GameClear();
		return WIN;																					//승리를 반환
	}

	else if (StopCmd == ESC)																		//esc가 눌려서 스탑커맨드가 esc일떄
		return ESC;																					//esc를 반환

	GameOver();																						//모두 아니어서 못탈출 했으면 게임오버
	Original																						//아무 색도 아닌걸로 초기화
		return 0;
}

void GameClear()																					//클리어 함수
{
	int x = 0, y = 12, count;
	clrscr();
	delay(300);
	for (count = 0; count <= 4; ++count)
	{
		gotoxy(x, y);
		Yellow puts("		  ■■■ ■       ■■■■   ■■   ■■■");							//클리어 문구 출력
		Yellow puts("		■       ■       ■       ■    ■ ■    ■");
		Yellow puts("		■       ■       ■■■■ ■■■■ ■■■■");
		Yellow puts("		■       ■       ■       ■    ■ ■  ■");
		Yellow puts("		  ■■■ ■■■■ ■■■■ ■    ■ ■    ■");
		gotoxy(x, y);

		Beep(DO + 262 + 420, 40);																	//음계 출력
		Beep(SOL + 262 + 420, 40);


		clrscr();																					//그리고 지우고를 0.1초마다 반복
		delay(100);
	}
	clrscr();																						//화면 지우기
	Original																						//색상 초기화
}

void GameOver()																						//게임 오버 함수
{
	int x = 0, y = 12, count;
	clrscr();																						//화면 청소
	for (count = 0; count <= 4; ++count)
	{
		gotoxy(x, y);
		Red puts("   ■■■   ■■   ■      ■ ■■■■   ■■■   ■      ■ ■■■■ ■■■");	//문구 출력
		Red puts(" ■       ■    ■ ■■  ■■ ■       ■      ■ ■      ■ ■       ■    ■");
		Red puts(" ■  ■■ ■■■■ ■  ■  ■ ■■■■ ■      ■  ■    ■  ■■■■ ■■■■");
		Red puts(" ■    ■ ■    ■ ■      ■ ■       ■      ■   ■  ■   ■       ■   ■");
		Red puts("   ■■■ ■    ■ ■      ■ ■■■■   ■■■       ■     ■■■■ ■    ■");
		gotoxy(x, y);

		Beep(SI + 262 + 420, 40);
		Beep(SOL + 262 + 420, 40);
		clrscr();																					//그리고 지우고를 0.2초마다 반복
		delay(200);
	}
	clrscr();																						//화면 지우기
	PrintMainScreen();																				//메뉴화면 출력
}

void Print_Block(BOOL show)																			//우리가 움직이는 블록 출력함수
{
	int i, temp = 0;
	//미리보기를 위한 부분
	while (GetAround(spin, block_x, block_y + temp + 1) == EMPTY || PREVIEW)						//주변이 비어있거나 미리보기 일때만
	{
		temp++;																						//y에 더해지는 temp값 1씩 더한다 즉 아래로 한칸씩
	}																								//미리보기 출력을 위해 어디에 미리보기를 보여줄지 정하는것
	for (i = 0; i < 4; i++)																			//블럭은 모두 4개의 좌표를 가짐 즉 4번의 출력
	{
		gotoxy(BX + (shape[block][spin][i].x + block_x) * 2, BY + shape[block][spin][i].y + block_y + temp);//미리보기 블록 좌표로 이동후
		puts(tile_style[show ? PREVIEW : EMPTY]);													//불에서 TRUE가 입력되면 미리보기 보이고 FALSE이면 빈공간으로 둔다
	}
	//미리보기 부분끝
	//미리보기와 그냥 블록출력에서 차이점은 y좌표에 temp값을 더해주냐 안더해주냐의 차이
	for (i = 0; i < 4; i++)																			//블럭은 모두 4개의 좌표를 가짐 즉 4번의 출력
	{
		gotoxy(BX + (shape[block][spin][i].x + block_x) * 2, BY + shape[block][spin][i].y + block_y);// 이건 그냥 블록의 위치
		puts(tile_style[show ? BRICK : EMPTY]);														//불에서 참이 출력되면 그리고 거짓이면 빈공간으로 둔다
	}
}

void Print_NextBlock(BOOL show)
{
	int i;
	for (i = 0; i < 4; i++)																			//블럭은 모두 4개의 좌표를 가짐 즉 4번의 출력
	{
		gotoxy(BX + (shape[next_block][0][i].x + 16) * 2, BY + shape[next_block][0][i].y + 6);		//다음 블록을 보여주는 위치설정 회전은 기본값
		puts(tile_style[show ? BRICK : EMPTY]);														//불에서 참이 출력되면 그리고 거짓이면 빈공간으로 둔다
	}
}

void Print_SaveBlock(BOOL show)																		//저장된 블록을 보여줌
{
	int i;
	for (i = 0; i < 4; i++)																			//블럭은 모두 4개의 좌표를 가짐 즉 4번의 출력
	{
		gotoxy(BX + (shape[save_block][0][i].x + 22) * 2, BY + shape[save_block][0][i].y + 6);		//저장 블록을 보여주는 위치설정 회전은 기본값
		puts(tile_style[show ? BRICK : EMPTY]);														//불에서 참이 출력되면 그리고 거짓이면 빈공간으로 둔다
	}
}

void BlockSave()										//블록 저장 함수
{																									//포인터 변수를 써서 함수 밖에서도 통하도록 한다
	int temp;																						//템프엔 변화될값 임시저장
	Print_Block(FALSE);																				//블록과 저장블록을 지운뒤
	Print_SaveBlock(FALSE);
	if (save_block == 7) save_block = next_block;												//블록값은 0~6이다 저장값이 7이면 저장된게 없으니 다음블록을 가져오라는뜻
	temp = block;																					//템프에 현재블록 저장
	block = save_block;																			//세이브 블록을 현재블록에 대입
	save_block = temp;																			//세이브 블록엔 원래 블록값이 담겨있는 템프를 대입
	if (block == 0 && block_x>8) block_x -= 1;													//길쭉이 블록일때 좌표가 길어서 생기던오류를 오른쪽 벽에 어느정도 가까울때만 왼쪽으로 한칸 옮겨지면서 바뀌게 바꿔서 고침
	BlockColor(save_block); Print_SaveBlock(TRUE);													//저장블록과 그냥블록을 출력
	BlockColor(block); Print_Block(TRUE);															//앞의 블록컬러는 색상 지정
}





void Clean_Save()																					//저장블록 출력공간 청소
{
	int x, y;
	for (x = 20; x < 49; x++)
	{
		for (y = 5; y < 9; y++)
		{
			board[x][y] = (EMPTY);																	//빈칸으로 표시
		}
	}
}


int SelectMenu()																					//메뉴 선택화살표 움직이는 함수
{
	int ch = _getch();																				//눌린키를 ch에 저장
	int MenuNum = 1;

	while (ch != ENTER)																				//엔터가 눌리기 전까지
	{
		if (_kbhit())																				//키보드가 눌린 유무를 판단함
		{
			ch = _getch();																			//바로 반응하는 명령어

			while (1)
			{
				ch = _getch();																		//다시한번 저장
				Sleep(5);																			//아주 조금 딜레이
				switch (ch)
				{

				case UP:
				{
					gotoxy(16, 16 + (2 * MenuNum));  High puts("▶");								//메뉴에 맞는 위치에 화살표를 위치시킴
					if (MenuNum == 1)																//1일경우에 위를 누르면 3으로 가도록
					{
						gotoxy(16, 16 + (2 * MenuNum));   High puts("  ");							//화살표를 지우고
						MenuNum = 3;																//옮긴뒤
						gotoxy(16, 16 + (2 * MenuNum));  High puts("▶");							//새화살표를 출력
					}
					else
					{
						gotoxy(16, 16 + (2 * MenuNum));   High puts("  ");
						MenuNum -= 1;
						gotoxy(16, 16 + (2 * MenuNum));  High puts("▶");
					}
					Beep(861, 20);																	//효과음
					break;
				}


				case DOWN:
				{
					gotoxy(16, 16 + (2 * MenuNum));  High puts("▶");
					if (MenuNum == 3)																//3일경우엔 아래누르면 1로 가도록
					{
						gotoxy(16, 16 + (2 * MenuNum));   High puts("  ");
						MenuNum = 1;
						gotoxy(16, 16 + (2 * MenuNum));   High puts("▶");
					}
					else
					{
						gotoxy(16, 16 + (2 * MenuNum));   High puts("  ");
						MenuNum += 1;
						gotoxy(16, 16 + (2 * MenuNum));  High puts("▶");
					}
					Beep(861, 20);
					break;
				}


				}
				if (ch == ENTER)																	//엔터가 눌리면
				{
					if (MenuNum != 3)																//3 즉 나가기가 아닐경우에 멜로디출력
					{
						Beep(461, 20);
						Beep(601, 20);
						Beep(801, 20);
						Beep(1061, 50);
					}
					else {}																			//나머지는 무시
					break;
				}
			}
		}
		break;
	}
	Original;
	return MenuNum;																					//고른 번호를 반환
}



int SelectLevel()																					//시작 레벨 설정용 함수 화살표 표시
{
	int ch = _getch();
	int game_Level = 1;

	while (ch != ENTER)
	{
		if (_kbhit())																				//키보드가 눌린 유무를 판단함
		{

			while (1)
			{
				ch = _getch();
				Sleep(5);
				switch (ch)
				{

				case UP:
				{
					gotoxy(16, 13 + 2 * (game_Level));   High; puts("▶");
					if (game_Level == 1)
					{
						gotoxy(16, 13 + 2 * (game_Level));  High; puts("  ");
						game_Level = 5;
						gotoxy(16, 13 + 2 * (game_Level));   High; puts("▶");
					}
					else
					{
						gotoxy(16, 13 + 2 * (game_Level));  High; puts("  ");
						game_Level -= 1;
						gotoxy(16, 13 + 2 * (game_Level));  High; puts("▶");
					}
					Beep(861, 20);
					break;
				}


				case DOWN:
				{
					gotoxy(16, 13 + 2 * (game_Level));   High; puts("▶");
					if (game_Level == 5)
					{
						gotoxy(16, 13 + 2 * (game_Level));   High; puts("  ");
						game_Level = 1;
						gotoxy(16, 13 + 2 * (game_Level));   High; puts("▶");
					}
					else
					{
						gotoxy(16, 13 + 2 * (game_Level));   High; puts("  ");
						game_Level += 1;
						gotoxy(16, 13 + 2 * (game_Level));   High; puts("▶");
					}
					Beep(861, 20);
					break;
				}


				}
				if (ch == ENTER)
				{
					Beep(461, 20);
					Beep(601, 20);
					Beep(801, 20);
					Beep(1061, 50);

					break;
				}
			}
		}
		break;
	}
	Original;
	return game_Level;
}

//이밑으로는 터보씨 관련 함수들
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 화면을 모두 지운다.
void clrscr()
{
	system("cls");
}

// 커서를 x,y좌표로 이동시킨다.
void gotoxy(int x, int y)
{
	COORD Cur;
	Cur.X = x;
	Cur.Y = y;
	SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), Cur);
}

// 커서의 x 좌표를 조사한다.
int wherex()
{
	CONSOLE_SCREEN_BUFFER_INFO BufInfo;

	GetConsoleScreenBufferInfo(GetStdHandle(STD_OUTPUT_HANDLE), &BufInfo);
	return BufInfo.dwCursorPosition.X;
}

// 커서의 y좌표를 조사한다.
int wherey()
{
	CONSOLE_SCREEN_BUFFER_INFO BufInfo;

	GetConsoleScreenBufferInfo(GetStdHandle(STD_OUTPUT_HANDLE), &BufInfo);
	return BufInfo.dwCursorPosition.Y;
}

// 커서를 숨기거나 다시 표시한다.
void setcursortype(CURSOR_TYPE c)
{
	CONSOLE_CURSOR_INFO CurInfo;

	switch (c) {
	case NOCURSOR:
		CurInfo.dwSize = 1;
		CurInfo.bVisible = FALSE;
		break;
	case SOLIDCURSOR:
		CurInfo.dwSize = 100;
		CurInfo.bVisible = TRUE;
		break;
	case NORMALCURSOR:
		CurInfo.dwSize = 20;
		CurInfo.bVisible = TRUE;
		break;
	}
	SetConsoleCursorInfo(GetStdHandle(STD_OUTPUT_HANDLE), &CurInfo);
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
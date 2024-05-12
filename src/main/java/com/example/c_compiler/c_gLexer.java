package com.example.c_compiler;// Generated from ./c_g.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class c_gLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WHITESPACE=1, COMMENT=2, LINE_COMMENT=3, FOR=4, WHILE=5, DO=6, IF=7, ELSE=8, 
		RETURN=9, CONTINUE=10, BREAK=11, SWITCH=12, CASE=13, DEFAULT=14, GOTO=15, 
		INT=16, FLOAT=17, DOUBLE=18, VOID=19, CHAR=20, ENUM=21, STRUCT=22, UNION=23, 
		CONST=24, VOLATILE=25, TYPEDEF=26, EXTERN=27, STATIC=28, AUTO=29, REGISTER=30, 
		SIGNED=31, UNSIGNED=32, SIZEOF=33, PTRDIFF=34, LONG=35, SHORT=36, INLINE=37, 
		COMPLEX=38, IMAGINARY=39, BOOL=40, BOOL_CONSTANT=41, ELLIPSIS=42, INT_CONSTANT=43, 
		FLOAT_CONSTANT=44, CHAR_CONSTANT=45, STRING_LITERAL=46, IDENTIFIER=47, 
		SEMICOLON=48, LBRACE=49, RBRACE=50, COMMA=51, COLON=52, ASSIGN=53, LPAREN=54, 
		RPAREN=55, LBRACKET=56, RBRACKET=57, QUESTION=58, OR=59, AND=60, BIT_OR=61, 
		BIT_XOR=62, BIT_AND=63, EQ=64, NE=65, LEFT_SHIFT=66, RIGHT_SHIFT=67, ADD=68, 
		SUB=69, MUL=70, DIV=71, MOD=72, MUL_ASSIGN=73, DIV_ASSIGN=74, MOD_ASSIGN=75, 
		ADD_ASSIGN=76, SUB_ASSIGN=77, LEFT_ASSIGN=78, RIGHT_ASSIGN=79, AND_ASSIGN=80, 
		XOR_ASSIGN=81, OR_ASSIGN=82, INC=83, DEC=84, LT=85, GT=86, LE=87, GE=88, 
		DOT=89, ARROW=90, BIT_NOT=91, NOT=92;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"LETTER", "ALPHA", "ALPHANUMERIC", "WHITESPACE", "COMMENT", "LINE_COMMENT", 
			"FOR", "WHILE", "DO", "IF", "ELSE", "RETURN", "CONTINUE", "BREAK", "SWITCH", 
			"CASE", "DEFAULT", "GOTO", "INT", "FLOAT", "DOUBLE", "VOID", "CHAR", 
			"ENUM", "STRUCT", "UNION", "CONST", "VOLATILE", "TYPEDEF", "EXTERN", 
			"STATIC", "AUTO", "REGISTER", "SIGNED", "UNSIGNED", "SIZEOF", "PTRDIFF", 
			"LONG", "SHORT", "INLINE", "COMPLEX", "IMAGINARY", "BOOL", "BOOL_CONSTANT", 
			"ELLIPSIS", "INT_CONSTANT", "FLOAT_CONSTANT", "CHAR_CONSTANT", "STRING_LITERAL", 
			"IDENTIFIER", "SEMICOLON", "LBRACE", "RBRACE", "COMMA", "COLON", "ASSIGN", 
			"LPAREN", "RPAREN", "LBRACKET", "RBRACKET", "QUESTION", "OR", "AND", 
			"BIT_OR", "BIT_XOR", "BIT_AND", "EQ", "NE", "LEFT_SHIFT", "RIGHT_SHIFT", 
			"ADD", "SUB", "MUL", "DIV", "MOD", "MUL_ASSIGN", "DIV_ASSIGN", "MOD_ASSIGN", 
			"ADD_ASSIGN", "SUB_ASSIGN", "LEFT_ASSIGN", "RIGHT_ASSIGN", "AND_ASSIGN", 
			"XOR_ASSIGN", "OR_ASSIGN", "INC", "DEC", "LT", "GT", "LE", "GE", "DOT", 
			"ARROW", "BIT_NOT", "NOT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, "'for'", "'while'", "'do'", "'if'", "'else'", 
			"'return'", "'continue'", "'break'", "'switch'", "'case'", "'default'", 
			"'goto'", "'int'", "'float'", "'double'", "'void'", "'char'", "'enum'", 
			"'struct'", "'union'", "'const'", "'volatile'", "'typedef'", "'extern'", 
			"'static'", "'auto'", "'register'", "'signed'", "'unsigned'", "'sizeof'", 
			"'ptrdiff_t'", "'long'", "'short'", "'inline'", "'_Complex'", "'_Imaginary'", 
			"'_Bool'", null, "'...'", null, null, null, null, null, "';'", "'{'", 
			"'}'", "','", "':'", "'='", "'('", "')'", "'['", "']'", "'?'", "'||'", 
			"'&&'", "'|'", "'^'", "'&'", "'=='", "'!='", "'<<'", "'>>'", "'+'", "'-'", 
			"'*'", "'/'", "'%'", "'*='", "'/='", "'%='", "'+='", "'-='", "'<<='", 
			"'>>='", "'&='", "'^='", "'|='", "'++'", "'--'", "'<'", "'>'", "'<='", 
			"'>='", "'.'", "'->'", "'~'", "'!'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "WHITESPACE", "COMMENT", "LINE_COMMENT", "FOR", "WHILE", "DO", 
			"IF", "ELSE", "RETURN", "CONTINUE", "BREAK", "SWITCH", "CASE", "DEFAULT", 
			"GOTO", "INT", "FLOAT", "DOUBLE", "VOID", "CHAR", "ENUM", "STRUCT", "UNION", 
			"CONST", "VOLATILE", "TYPEDEF", "EXTERN", "STATIC", "AUTO", "REGISTER", 
			"SIGNED", "UNSIGNED", "SIZEOF", "PTRDIFF", "LONG", "SHORT", "INLINE", 
			"COMPLEX", "IMAGINARY", "BOOL", "BOOL_CONSTANT", "ELLIPSIS", "INT_CONSTANT", 
			"FLOAT_CONSTANT", "CHAR_CONSTANT", "STRING_LITERAL", "IDENTIFIER", "SEMICOLON", 
			"LBRACE", "RBRACE", "COMMA", "COLON", "ASSIGN", "LPAREN", "RPAREN", "LBRACKET", 
			"RBRACKET", "QUESTION", "OR", "AND", "BIT_OR", "BIT_XOR", "BIT_AND", 
			"EQ", "NE", "LEFT_SHIFT", "RIGHT_SHIFT", "ADD", "SUB", "MUL", "DIV", 
			"MOD", "MUL_ASSIGN", "DIV_ASSIGN", "MOD_ASSIGN", "ADD_ASSIGN", "SUB_ASSIGN", 
			"LEFT_ASSIGN", "RIGHT_ASSIGN", "AND_ASSIGN", "XOR_ASSIGN", "OR_ASSIGN", 
			"INC", "DEC", "LT", "GT", "LE", "GE", "DOT", "ARROW", "BIT_NOT", "NOT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	public c_gLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "c_g.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\\\u0287\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002"+
		"\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002"+
		"\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002"+
		"\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007"+
		"!\u0002\"\u0007\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007"+
		"&\u0002\'\u0007\'\u0002(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007"+
		"+\u0002,\u0007,\u0002-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u0007"+
		"0\u00021\u00071\u00022\u00072\u00023\u00073\u00024\u00074\u00025\u0007"+
		"5\u00026\u00076\u00027\u00077\u00028\u00078\u00029\u00079\u0002:\u0007"+
		":\u0002;\u0007;\u0002<\u0007<\u0002=\u0007=\u0002>\u0007>\u0002?\u0007"+
		"?\u0002@\u0007@\u0002A\u0007A\u0002B\u0007B\u0002C\u0007C\u0002D\u0007"+
		"D\u0002E\u0007E\u0002F\u0007F\u0002G\u0007G\u0002H\u0007H\u0002I\u0007"+
		"I\u0002J\u0007J\u0002K\u0007K\u0002L\u0007L\u0002M\u0007M\u0002N\u0007"+
		"N\u0002O\u0007O\u0002P\u0007P\u0002Q\u0007Q\u0002R\u0007R\u0002S\u0007"+
		"S\u0002T\u0007T\u0002U\u0007U\u0002V\u0007V\u0002W\u0007W\u0002X\u0007"+
		"X\u0002Y\u0007Y\u0002Z\u0007Z\u0002[\u0007[\u0002\\\u0007\\\u0002]\u0007"+
		"]\u0002^\u0007^\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0003"+
		"\u0001\u00c4\b\u0001\u0001\u0002\u0001\u0002\u0003\u0002\u00c8\b\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0005\u0004\u00d2\b\u0004\n\u0004\f\u0004\u00d5"+
		"\t\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005\u00e0\b\u0005\n"+
		"\u0005\f\u0005\u00e3\t\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t"+
		"\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d"+
		"\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f"+
		"\u0001\u001f\u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001"+
		" \u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001\"\u0001\"\u0001"+
		"\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001#\u0001#\u0001"+
		"#\u0001#\u0001#\u0001#\u0001#\u0001$\u0001$\u0001$\u0001$\u0001$\u0001"+
		"$\u0001$\u0001$\u0001$\u0001$\u0001%\u0001%\u0001%\u0001%\u0001%\u0001"+
		"&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001\'\u0001\'\u0001\'\u0001\'\u0001"+
		"\'\u0001\'\u0001\'\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001"+
		"(\u0001(\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001"+
		")\u0001)\u0001)\u0001*\u0001*\u0001*\u0001*\u0001*\u0001*\u0001+\u0001"+
		"+\u0001+\u0001+\u0001+\u0001+\u0001+\u0001+\u0001+\u0003+\u01e1\b+\u0001"+
		",\u0001,\u0001,\u0001,\u0001-\u0004-\u01e8\b-\u000b-\f-\u01e9\u0001.\u0004"+
		".\u01ed\b.\u000b.\f.\u01ee\u0001.\u0001.\u0005.\u01f3\b.\n.\f.\u01f6\t"+
		".\u0001.\u0001.\u0003.\u01fa\b.\u0001.\u0004.\u01fd\b.\u000b.\f.\u01fe"+
		"\u0003.\u0201\b.\u0001/\u0001/\u0001/\u0001/\u00010\u00010\u00050\u0209"+
		"\b0\n0\f0\u020c\t0\u00010\u00010\u00011\u00011\u00051\u0212\b1\n1\f1\u0215"+
		"\t1\u00012\u00012\u00013\u00013\u00014\u00014\u00015\u00015\u00016\u0001"+
		"6\u00017\u00017\u00018\u00018\u00019\u00019\u0001:\u0001:\u0001;\u0001"+
		";\u0001<\u0001<\u0001=\u0001=\u0001=\u0001>\u0001>\u0001>\u0001?\u0001"+
		"?\u0001@\u0001@\u0001A\u0001A\u0001B\u0001B\u0001B\u0001C\u0001C\u0001"+
		"C\u0001D\u0001D\u0001D\u0001E\u0001E\u0001E\u0001F\u0001F\u0001G\u0001"+
		"G\u0001H\u0001H\u0001I\u0001I\u0001J\u0001J\u0001K\u0001K\u0001K\u0001"+
		"L\u0001L\u0001L\u0001M\u0001M\u0001M\u0001N\u0001N\u0001N\u0001O\u0001"+
		"O\u0001O\u0001P\u0001P\u0001P\u0001P\u0001Q\u0001Q\u0001Q\u0001Q\u0001"+
		"R\u0001R\u0001R\u0001S\u0001S\u0001S\u0001T\u0001T\u0001T\u0001U\u0001"+
		"U\u0001U\u0001V\u0001V\u0001V\u0001W\u0001W\u0001X\u0001X\u0001Y\u0001"+
		"Y\u0001Y\u0001Z\u0001Z\u0001Z\u0001[\u0001[\u0001\\\u0001\\\u0001\\\u0001"+
		"]\u0001]\u0001^\u0001^\u0001\u00d3\u0000_\u0001\u0000\u0003\u0000\u0005"+
		"\u0000\u0007\u0001\t\u0002\u000b\u0003\r\u0004\u000f\u0005\u0011\u0006"+
		"\u0013\u0007\u0015\b\u0017\t\u0019\n\u001b\u000b\u001d\f\u001f\r!\u000e"+
		"#\u000f%\u0010\'\u0011)\u0012+\u0013-\u0014/\u00151\u00163\u00175\u0018"+
		"7\u00199\u001a;\u001b=\u001c?\u001dA\u001eC\u001fE G!I\"K#M$O%Q&S\'U("+
		"W)Y*[+],_-a.c/e0g1i2k3m4o5q6s7u8w9y:{;}<\u007f=\u0081>\u0083?\u0085@\u0087"+
		"A\u0089B\u008bC\u008dD\u008fE\u0091F\u0093G\u0095H\u0097I\u0099J\u009b"+
		"K\u009dL\u009fM\u00a1N\u00a3O\u00a5P\u00a7Q\u00a9R\u00abS\u00adT\u00af"+
		"U\u00b1V\u00b3W\u00b5X\u00b7Y\u00b9Z\u00bb[\u00bd\\\u0001\u0000\u0007"+
		"\u0002\u0000AZaz\u0001\u000009\u0003\u0000\t\n\r\r  \u0002\u0000\n\n\r"+
		"\r\u0002\u0000EEee\u0002\u0000++--\u0003\u0000\n\n\r\r\"\"\u0290\u0000"+
		"\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b"+
		"\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001"+
		"\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001"+
		"\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001"+
		"\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001"+
		"\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001"+
		"\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000"+
		"\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000"+
		"\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000-"+
		"\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000\u00001\u0001\u0000"+
		"\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u00005\u0001\u0000\u0000\u0000"+
		"\u00007\u0001\u0000\u0000\u0000\u00009\u0001\u0000\u0000\u0000\u0000;"+
		"\u0001\u0000\u0000\u0000\u0000=\u0001\u0000\u0000\u0000\u0000?\u0001\u0000"+
		"\u0000\u0000\u0000A\u0001\u0000\u0000\u0000\u0000C\u0001\u0000\u0000\u0000"+
		"\u0000E\u0001\u0000\u0000\u0000\u0000G\u0001\u0000\u0000\u0000\u0000I"+
		"\u0001\u0000\u0000\u0000\u0000K\u0001\u0000\u0000\u0000\u0000M\u0001\u0000"+
		"\u0000\u0000\u0000O\u0001\u0000\u0000\u0000\u0000Q\u0001\u0000\u0000\u0000"+
		"\u0000S\u0001\u0000\u0000\u0000\u0000U\u0001\u0000\u0000\u0000\u0000W"+
		"\u0001\u0000\u0000\u0000\u0000Y\u0001\u0000\u0000\u0000\u0000[\u0001\u0000"+
		"\u0000\u0000\u0000]\u0001\u0000\u0000\u0000\u0000_\u0001\u0000\u0000\u0000"+
		"\u0000a\u0001\u0000\u0000\u0000\u0000c\u0001\u0000\u0000\u0000\u0000e"+
		"\u0001\u0000\u0000\u0000\u0000g\u0001\u0000\u0000\u0000\u0000i\u0001\u0000"+
		"\u0000\u0000\u0000k\u0001\u0000\u0000\u0000\u0000m\u0001\u0000\u0000\u0000"+
		"\u0000o\u0001\u0000\u0000\u0000\u0000q\u0001\u0000\u0000\u0000\u0000s"+
		"\u0001\u0000\u0000\u0000\u0000u\u0001\u0000\u0000\u0000\u0000w\u0001\u0000"+
		"\u0000\u0000\u0000y\u0001\u0000\u0000\u0000\u0000{\u0001\u0000\u0000\u0000"+
		"\u0000}\u0001\u0000\u0000\u0000\u0000\u007f\u0001\u0000\u0000\u0000\u0000"+
		"\u0081\u0001\u0000\u0000\u0000\u0000\u0083\u0001\u0000\u0000\u0000\u0000"+
		"\u0085\u0001\u0000\u0000\u0000\u0000\u0087\u0001\u0000\u0000\u0000\u0000"+
		"\u0089\u0001\u0000\u0000\u0000\u0000\u008b\u0001\u0000\u0000\u0000\u0000"+
		"\u008d\u0001\u0000\u0000\u0000\u0000\u008f\u0001\u0000\u0000\u0000\u0000"+
		"\u0091\u0001\u0000\u0000\u0000\u0000\u0093\u0001\u0000\u0000\u0000\u0000"+
		"\u0095\u0001\u0000\u0000\u0000\u0000\u0097\u0001\u0000\u0000\u0000\u0000"+
		"\u0099\u0001\u0000\u0000\u0000\u0000\u009b\u0001\u0000\u0000\u0000\u0000"+
		"\u009d\u0001\u0000\u0000\u0000\u0000\u009f\u0001\u0000\u0000\u0000\u0000"+
		"\u00a1\u0001\u0000\u0000\u0000\u0000\u00a3\u0001\u0000\u0000\u0000\u0000"+
		"\u00a5\u0001\u0000\u0000\u0000\u0000\u00a7\u0001\u0000\u0000\u0000\u0000"+
		"\u00a9\u0001\u0000\u0000\u0000\u0000\u00ab\u0001\u0000\u0000\u0000\u0000"+
		"\u00ad\u0001\u0000\u0000\u0000\u0000\u00af\u0001\u0000\u0000\u0000\u0000"+
		"\u00b1\u0001\u0000\u0000\u0000\u0000\u00b3\u0001\u0000\u0000\u0000\u0000"+
		"\u00b5\u0001\u0000\u0000\u0000\u0000\u00b7\u0001\u0000\u0000\u0000\u0000"+
		"\u00b9\u0001\u0000\u0000\u0000\u0000\u00bb\u0001\u0000\u0000\u0000\u0000"+
		"\u00bd\u0001\u0000\u0000\u0000\u0001\u00bf\u0001\u0000\u0000\u0000\u0003"+
		"\u00c3\u0001\u0000\u0000\u0000\u0005\u00c7\u0001\u0000\u0000\u0000\u0007"+
		"\u00c9\u0001\u0000\u0000\u0000\t\u00cd\u0001\u0000\u0000\u0000\u000b\u00db"+
		"\u0001\u0000\u0000\u0000\r\u00e6\u0001\u0000\u0000\u0000\u000f\u00ea\u0001"+
		"\u0000\u0000\u0000\u0011\u00f0\u0001\u0000\u0000\u0000\u0013\u00f3\u0001"+
		"\u0000\u0000\u0000\u0015\u00f6\u0001\u0000\u0000\u0000\u0017\u00fb\u0001"+
		"\u0000\u0000\u0000\u0019\u0102\u0001\u0000\u0000\u0000\u001b\u010b\u0001"+
		"\u0000\u0000\u0000\u001d\u0111\u0001\u0000\u0000\u0000\u001f\u0118\u0001"+
		"\u0000\u0000\u0000!\u011d\u0001\u0000\u0000\u0000#\u0125\u0001\u0000\u0000"+
		"\u0000%\u012a\u0001\u0000\u0000\u0000\'\u012e\u0001\u0000\u0000\u0000"+
		")\u0134\u0001\u0000\u0000\u0000+\u013b\u0001\u0000\u0000\u0000-\u0140"+
		"\u0001\u0000\u0000\u0000/\u0145\u0001\u0000\u0000\u00001\u014a\u0001\u0000"+
		"\u0000\u00003\u0151\u0001\u0000\u0000\u00005\u0157\u0001\u0000\u0000\u0000"+
		"7\u015d\u0001\u0000\u0000\u00009\u0166\u0001\u0000\u0000\u0000;\u016e"+
		"\u0001\u0000\u0000\u0000=\u0175\u0001\u0000\u0000\u0000?\u017c\u0001\u0000"+
		"\u0000\u0000A\u0181\u0001\u0000\u0000\u0000C\u018a\u0001\u0000\u0000\u0000"+
		"E\u0191\u0001\u0000\u0000\u0000G\u019a\u0001\u0000\u0000\u0000I\u01a1"+
		"\u0001\u0000\u0000\u0000K\u01ab\u0001\u0000\u0000\u0000M\u01b0\u0001\u0000"+
		"\u0000\u0000O\u01b6\u0001\u0000\u0000\u0000Q\u01bd\u0001\u0000\u0000\u0000"+
		"S\u01c6\u0001\u0000\u0000\u0000U\u01d1\u0001\u0000\u0000\u0000W\u01e0"+
		"\u0001\u0000\u0000\u0000Y\u01e2\u0001\u0000\u0000\u0000[\u01e7\u0001\u0000"+
		"\u0000\u0000]\u01ec\u0001\u0000\u0000\u0000_\u0202\u0001\u0000\u0000\u0000"+
		"a\u0206\u0001\u0000\u0000\u0000c\u020f\u0001\u0000\u0000\u0000e\u0216"+
		"\u0001\u0000\u0000\u0000g\u0218\u0001\u0000\u0000\u0000i\u021a\u0001\u0000"+
		"\u0000\u0000k\u021c\u0001\u0000\u0000\u0000m\u021e\u0001\u0000\u0000\u0000"+
		"o\u0220\u0001\u0000\u0000\u0000q\u0222\u0001\u0000\u0000\u0000s\u0224"+
		"\u0001\u0000\u0000\u0000u\u0226\u0001\u0000\u0000\u0000w\u0228\u0001\u0000"+
		"\u0000\u0000y\u022a\u0001\u0000\u0000\u0000{\u022c\u0001\u0000\u0000\u0000"+
		"}\u022f\u0001\u0000\u0000\u0000\u007f\u0232\u0001\u0000\u0000\u0000\u0081"+
		"\u0234\u0001\u0000\u0000\u0000\u0083\u0236\u0001\u0000\u0000\u0000\u0085"+
		"\u0238\u0001\u0000\u0000\u0000\u0087\u023b\u0001\u0000\u0000\u0000\u0089"+
		"\u023e\u0001\u0000\u0000\u0000\u008b\u0241\u0001\u0000\u0000\u0000\u008d"+
		"\u0244\u0001\u0000\u0000\u0000\u008f\u0246\u0001\u0000\u0000\u0000\u0091"+
		"\u0248\u0001\u0000\u0000\u0000\u0093\u024a\u0001\u0000\u0000\u0000\u0095"+
		"\u024c\u0001\u0000\u0000\u0000\u0097\u024e\u0001\u0000\u0000\u0000\u0099"+
		"\u0251\u0001\u0000\u0000\u0000\u009b\u0254\u0001\u0000\u0000\u0000\u009d"+
		"\u0257\u0001\u0000\u0000\u0000\u009f\u025a\u0001\u0000\u0000\u0000\u00a1"+
		"\u025d\u0001\u0000\u0000\u0000\u00a3\u0261\u0001\u0000\u0000\u0000\u00a5"+
		"\u0265\u0001\u0000\u0000\u0000\u00a7\u0268\u0001\u0000\u0000\u0000\u00a9"+
		"\u026b\u0001\u0000\u0000\u0000\u00ab\u026e\u0001\u0000\u0000\u0000\u00ad"+
		"\u0271\u0001\u0000\u0000\u0000\u00af\u0274\u0001\u0000\u0000\u0000\u00b1"+
		"\u0276\u0001\u0000\u0000\u0000\u00b3\u0278\u0001\u0000\u0000\u0000\u00b5"+
		"\u027b\u0001\u0000\u0000\u0000\u00b7\u027e\u0001\u0000\u0000\u0000\u00b9"+
		"\u0280\u0001\u0000\u0000\u0000\u00bb\u0283\u0001\u0000\u0000\u0000\u00bd"+
		"\u0285\u0001\u0000\u0000\u0000\u00bf\u00c0\u0007\u0000\u0000\u0000\u00c0"+
		"\u0002\u0001\u0000\u0000\u0000\u00c1\u00c4\u0003\u0001\u0000\u0000\u00c2"+
		"\u00c4\u0005_\u0000\u0000\u00c3\u00c1\u0001\u0000\u0000\u0000\u00c3\u00c2"+
		"\u0001\u0000\u0000\u0000\u00c4\u0004\u0001\u0000\u0000\u0000\u00c5\u00c8"+
		"\u0003\u0003\u0001\u0000\u00c6\u00c8\u0007\u0001\u0000\u0000\u00c7\u00c5"+
		"\u0001\u0000\u0000\u0000\u00c7\u00c6\u0001\u0000\u0000\u0000\u00c8\u0006"+
		"\u0001\u0000\u0000\u0000\u00c9\u00ca\u0007\u0002\u0000\u0000\u00ca\u00cb"+
		"\u0001\u0000\u0000\u0000\u00cb\u00cc\u0006\u0003\u0000\u0000\u00cc\b\u0001"+
		"\u0000\u0000\u0000\u00cd\u00ce\u0005/\u0000\u0000\u00ce\u00cf\u0005*\u0000"+
		"\u0000\u00cf\u00d3\u0001\u0000\u0000\u0000\u00d0\u00d2\t\u0000\u0000\u0000"+
		"\u00d1\u00d0\u0001\u0000\u0000\u0000\u00d2\u00d5\u0001\u0000\u0000\u0000"+
		"\u00d3\u00d4\u0001\u0000\u0000\u0000\u00d3\u00d1\u0001\u0000\u0000\u0000"+
		"\u00d4\u00d6\u0001\u0000\u0000\u0000\u00d5\u00d3\u0001\u0000\u0000\u0000"+
		"\u00d6\u00d7\u0005*\u0000\u0000\u00d7\u00d8\u0005/\u0000\u0000\u00d8\u00d9"+
		"\u0001\u0000\u0000\u0000\u00d9\u00da\u0006\u0004\u0000\u0000\u00da\n\u0001"+
		"\u0000\u0000\u0000\u00db\u00dc\u0005/\u0000\u0000\u00dc\u00dd\u0005/\u0000"+
		"\u0000\u00dd\u00e1\u0001\u0000\u0000\u0000\u00de\u00e0\b\u0003\u0000\u0000"+
		"\u00df\u00de\u0001\u0000\u0000\u0000\u00e0\u00e3\u0001\u0000\u0000\u0000"+
		"\u00e1\u00df\u0001\u0000\u0000\u0000\u00e1\u00e2\u0001\u0000\u0000\u0000"+
		"\u00e2\u00e4\u0001\u0000\u0000\u0000\u00e3\u00e1\u0001\u0000\u0000\u0000"+
		"\u00e4\u00e5\u0006\u0005\u0000\u0000\u00e5\f\u0001\u0000\u0000\u0000\u00e6"+
		"\u00e7\u0005f\u0000\u0000\u00e7\u00e8\u0005o\u0000\u0000\u00e8\u00e9\u0005"+
		"r\u0000\u0000\u00e9\u000e\u0001\u0000\u0000\u0000\u00ea\u00eb\u0005w\u0000"+
		"\u0000\u00eb\u00ec\u0005h\u0000\u0000\u00ec\u00ed\u0005i\u0000\u0000\u00ed"+
		"\u00ee\u0005l\u0000\u0000\u00ee\u00ef\u0005e\u0000\u0000\u00ef\u0010\u0001"+
		"\u0000\u0000\u0000\u00f0\u00f1\u0005d\u0000\u0000\u00f1\u00f2\u0005o\u0000"+
		"\u0000\u00f2\u0012\u0001\u0000\u0000\u0000\u00f3\u00f4\u0005i\u0000\u0000"+
		"\u00f4\u00f5\u0005f\u0000\u0000\u00f5\u0014\u0001\u0000\u0000\u0000\u00f6"+
		"\u00f7\u0005e\u0000\u0000\u00f7\u00f8\u0005l\u0000\u0000\u00f8\u00f9\u0005"+
		"s\u0000\u0000\u00f9\u00fa\u0005e\u0000\u0000\u00fa\u0016\u0001\u0000\u0000"+
		"\u0000\u00fb\u00fc\u0005r\u0000\u0000\u00fc\u00fd\u0005e\u0000\u0000\u00fd"+
		"\u00fe\u0005t\u0000\u0000\u00fe\u00ff\u0005u\u0000\u0000\u00ff\u0100\u0005"+
		"r\u0000\u0000\u0100\u0101\u0005n\u0000\u0000\u0101\u0018\u0001\u0000\u0000"+
		"\u0000\u0102\u0103\u0005c\u0000\u0000\u0103\u0104\u0005o\u0000\u0000\u0104"+
		"\u0105\u0005n\u0000\u0000\u0105\u0106\u0005t\u0000\u0000\u0106\u0107\u0005"+
		"i\u0000\u0000\u0107\u0108\u0005n\u0000\u0000\u0108\u0109\u0005u\u0000"+
		"\u0000\u0109\u010a\u0005e\u0000\u0000\u010a\u001a\u0001\u0000\u0000\u0000"+
		"\u010b\u010c\u0005b\u0000\u0000\u010c\u010d\u0005r\u0000\u0000\u010d\u010e"+
		"\u0005e\u0000\u0000\u010e\u010f\u0005a\u0000\u0000\u010f\u0110\u0005k"+
		"\u0000\u0000\u0110\u001c\u0001\u0000\u0000\u0000\u0111\u0112\u0005s\u0000"+
		"\u0000\u0112\u0113\u0005w\u0000\u0000\u0113\u0114\u0005i\u0000\u0000\u0114"+
		"\u0115\u0005t\u0000\u0000\u0115\u0116\u0005c\u0000\u0000\u0116\u0117\u0005"+
		"h\u0000\u0000\u0117\u001e\u0001\u0000\u0000\u0000\u0118\u0119\u0005c\u0000"+
		"\u0000\u0119\u011a\u0005a\u0000\u0000\u011a\u011b\u0005s\u0000\u0000\u011b"+
		"\u011c\u0005e\u0000\u0000\u011c \u0001\u0000\u0000\u0000\u011d\u011e\u0005"+
		"d\u0000\u0000\u011e\u011f\u0005e\u0000\u0000\u011f\u0120\u0005f\u0000"+
		"\u0000\u0120\u0121\u0005a\u0000\u0000\u0121\u0122\u0005u\u0000\u0000\u0122"+
		"\u0123\u0005l\u0000\u0000\u0123\u0124\u0005t\u0000\u0000\u0124\"\u0001"+
		"\u0000\u0000\u0000\u0125\u0126\u0005g\u0000\u0000\u0126\u0127\u0005o\u0000"+
		"\u0000\u0127\u0128\u0005t\u0000\u0000\u0128\u0129\u0005o\u0000\u0000\u0129"+
		"$\u0001\u0000\u0000\u0000\u012a\u012b\u0005i\u0000\u0000\u012b\u012c\u0005"+
		"n\u0000\u0000\u012c\u012d\u0005t\u0000\u0000\u012d&\u0001\u0000\u0000"+
		"\u0000\u012e\u012f\u0005f\u0000\u0000\u012f\u0130\u0005l\u0000\u0000\u0130"+
		"\u0131\u0005o\u0000\u0000\u0131\u0132\u0005a\u0000\u0000\u0132\u0133\u0005"+
		"t\u0000\u0000\u0133(\u0001\u0000\u0000\u0000\u0134\u0135\u0005d\u0000"+
		"\u0000\u0135\u0136\u0005o\u0000\u0000\u0136\u0137\u0005u\u0000\u0000\u0137"+
		"\u0138\u0005b\u0000\u0000\u0138\u0139\u0005l\u0000\u0000\u0139\u013a\u0005"+
		"e\u0000\u0000\u013a*\u0001\u0000\u0000\u0000\u013b\u013c\u0005v\u0000"+
		"\u0000\u013c\u013d\u0005o\u0000\u0000\u013d\u013e\u0005i\u0000\u0000\u013e"+
		"\u013f\u0005d\u0000\u0000\u013f,\u0001\u0000\u0000\u0000\u0140\u0141\u0005"+
		"c\u0000\u0000\u0141\u0142\u0005h\u0000\u0000\u0142\u0143\u0005a\u0000"+
		"\u0000\u0143\u0144\u0005r\u0000\u0000\u0144.\u0001\u0000\u0000\u0000\u0145"+
		"\u0146\u0005e\u0000\u0000\u0146\u0147\u0005n\u0000\u0000\u0147\u0148\u0005"+
		"u\u0000\u0000\u0148\u0149\u0005m\u0000\u0000\u01490\u0001\u0000\u0000"+
		"\u0000\u014a\u014b\u0005s\u0000\u0000\u014b\u014c\u0005t\u0000\u0000\u014c"+
		"\u014d\u0005r\u0000\u0000\u014d\u014e\u0005u\u0000\u0000\u014e\u014f\u0005"+
		"c\u0000\u0000\u014f\u0150\u0005t\u0000\u0000\u01502\u0001\u0000\u0000"+
		"\u0000\u0151\u0152\u0005u\u0000\u0000\u0152\u0153\u0005n\u0000\u0000\u0153"+
		"\u0154\u0005i\u0000\u0000\u0154\u0155\u0005o\u0000\u0000\u0155\u0156\u0005"+
		"n\u0000\u0000\u01564\u0001\u0000\u0000\u0000\u0157\u0158\u0005c\u0000"+
		"\u0000\u0158\u0159\u0005o\u0000\u0000\u0159\u015a\u0005n\u0000\u0000\u015a"+
		"\u015b\u0005s\u0000\u0000\u015b\u015c\u0005t\u0000\u0000\u015c6\u0001"+
		"\u0000\u0000\u0000\u015d\u015e\u0005v\u0000\u0000\u015e\u015f\u0005o\u0000"+
		"\u0000\u015f\u0160\u0005l\u0000\u0000\u0160\u0161\u0005a\u0000\u0000\u0161"+
		"\u0162\u0005t\u0000\u0000\u0162\u0163\u0005i\u0000\u0000\u0163\u0164\u0005"+
		"l\u0000\u0000\u0164\u0165\u0005e\u0000\u0000\u01658\u0001\u0000\u0000"+
		"\u0000\u0166\u0167\u0005t\u0000\u0000\u0167\u0168\u0005y\u0000\u0000\u0168"+
		"\u0169\u0005p\u0000\u0000\u0169\u016a\u0005e\u0000\u0000\u016a\u016b\u0005"+
		"d\u0000\u0000\u016b\u016c\u0005e\u0000\u0000\u016c\u016d\u0005f\u0000"+
		"\u0000\u016d:\u0001\u0000\u0000\u0000\u016e\u016f\u0005e\u0000\u0000\u016f"+
		"\u0170\u0005x\u0000\u0000\u0170\u0171\u0005t\u0000\u0000\u0171\u0172\u0005"+
		"e\u0000\u0000\u0172\u0173\u0005r\u0000\u0000\u0173\u0174\u0005n\u0000"+
		"\u0000\u0174<\u0001\u0000\u0000\u0000\u0175\u0176\u0005s\u0000\u0000\u0176"+
		"\u0177\u0005t\u0000\u0000\u0177\u0178\u0005a\u0000\u0000\u0178\u0179\u0005"+
		"t\u0000\u0000\u0179\u017a\u0005i\u0000\u0000\u017a\u017b\u0005c\u0000"+
		"\u0000\u017b>\u0001\u0000\u0000\u0000\u017c\u017d\u0005a\u0000\u0000\u017d"+
		"\u017e\u0005u\u0000\u0000\u017e\u017f\u0005t\u0000\u0000\u017f\u0180\u0005"+
		"o\u0000\u0000\u0180@\u0001\u0000\u0000\u0000\u0181\u0182\u0005r\u0000"+
		"\u0000\u0182\u0183\u0005e\u0000\u0000\u0183\u0184\u0005g\u0000\u0000\u0184"+
		"\u0185\u0005i\u0000\u0000\u0185\u0186\u0005s\u0000\u0000\u0186\u0187\u0005"+
		"t\u0000\u0000\u0187\u0188\u0005e\u0000\u0000\u0188\u0189\u0005r\u0000"+
		"\u0000\u0189B\u0001\u0000\u0000\u0000\u018a\u018b\u0005s\u0000\u0000\u018b"+
		"\u018c\u0005i\u0000\u0000\u018c\u018d\u0005g\u0000\u0000\u018d\u018e\u0005"+
		"n\u0000\u0000\u018e\u018f\u0005e\u0000\u0000\u018f\u0190\u0005d\u0000"+
		"\u0000\u0190D\u0001\u0000\u0000\u0000\u0191\u0192\u0005u\u0000\u0000\u0192"+
		"\u0193\u0005n\u0000\u0000\u0193\u0194\u0005s\u0000\u0000\u0194\u0195\u0005"+
		"i\u0000\u0000\u0195\u0196\u0005g\u0000\u0000\u0196\u0197\u0005n\u0000"+
		"\u0000\u0197\u0198\u0005e\u0000\u0000\u0198\u0199\u0005d\u0000\u0000\u0199"+
		"F\u0001\u0000\u0000\u0000\u019a\u019b\u0005s\u0000\u0000\u019b\u019c\u0005"+
		"i\u0000\u0000\u019c\u019d\u0005z\u0000\u0000\u019d\u019e\u0005e\u0000"+
		"\u0000\u019e\u019f\u0005o\u0000\u0000\u019f\u01a0\u0005f\u0000\u0000\u01a0"+
		"H\u0001\u0000\u0000\u0000\u01a1\u01a2\u0005p\u0000\u0000\u01a2\u01a3\u0005"+
		"t\u0000\u0000\u01a3\u01a4\u0005r\u0000\u0000\u01a4\u01a5\u0005d\u0000"+
		"\u0000\u01a5\u01a6\u0005i\u0000\u0000\u01a6\u01a7\u0005f\u0000\u0000\u01a7"+
		"\u01a8\u0005f\u0000\u0000\u01a8\u01a9\u0005_\u0000\u0000\u01a9\u01aa\u0005"+
		"t\u0000\u0000\u01aaJ\u0001\u0000\u0000\u0000\u01ab\u01ac\u0005l\u0000"+
		"\u0000\u01ac\u01ad\u0005o\u0000\u0000\u01ad\u01ae\u0005n\u0000\u0000\u01ae"+
		"\u01af\u0005g\u0000\u0000\u01afL\u0001\u0000\u0000\u0000\u01b0\u01b1\u0005"+
		"s\u0000\u0000\u01b1\u01b2\u0005h\u0000\u0000\u01b2\u01b3\u0005o\u0000"+
		"\u0000\u01b3\u01b4\u0005r\u0000\u0000\u01b4\u01b5\u0005t\u0000\u0000\u01b5"+
		"N\u0001\u0000\u0000\u0000\u01b6\u01b7\u0005i\u0000\u0000\u01b7\u01b8\u0005"+
		"n\u0000\u0000\u01b8\u01b9\u0005l\u0000\u0000\u01b9\u01ba\u0005i\u0000"+
		"\u0000\u01ba\u01bb\u0005n\u0000\u0000\u01bb\u01bc\u0005e\u0000\u0000\u01bc"+
		"P\u0001\u0000\u0000\u0000\u01bd\u01be\u0005_\u0000\u0000\u01be\u01bf\u0005"+
		"C\u0000\u0000\u01bf\u01c0\u0005o\u0000\u0000\u01c0\u01c1\u0005m\u0000"+
		"\u0000\u01c1\u01c2\u0005p\u0000\u0000\u01c2\u01c3\u0005l\u0000\u0000\u01c3"+
		"\u01c4\u0005e\u0000\u0000\u01c4\u01c5\u0005x\u0000\u0000\u01c5R\u0001"+
		"\u0000\u0000\u0000\u01c6\u01c7\u0005_\u0000\u0000\u01c7\u01c8\u0005I\u0000"+
		"\u0000\u01c8\u01c9\u0005m\u0000\u0000\u01c9\u01ca\u0005a\u0000\u0000\u01ca"+
		"\u01cb\u0005g\u0000\u0000\u01cb\u01cc\u0005i\u0000\u0000\u01cc\u01cd\u0005"+
		"n\u0000\u0000\u01cd\u01ce\u0005a\u0000\u0000\u01ce\u01cf\u0005r\u0000"+
		"\u0000\u01cf\u01d0\u0005y\u0000\u0000\u01d0T\u0001\u0000\u0000\u0000\u01d1"+
		"\u01d2\u0005_\u0000\u0000\u01d2\u01d3\u0005B\u0000\u0000\u01d3\u01d4\u0005"+
		"o\u0000\u0000\u01d4\u01d5\u0005o\u0000\u0000\u01d5\u01d6\u0005l\u0000"+
		"\u0000\u01d6V\u0001\u0000\u0000\u0000\u01d7\u01d8\u0005t\u0000\u0000\u01d8"+
		"\u01d9\u0005r\u0000\u0000\u01d9\u01da\u0005u\u0000\u0000\u01da\u01e1\u0005"+
		"e\u0000\u0000\u01db\u01dc\u0005f\u0000\u0000\u01dc\u01dd\u0005a\u0000"+
		"\u0000\u01dd\u01de\u0005l\u0000\u0000\u01de\u01df\u0005s\u0000\u0000\u01df"+
		"\u01e1\u0005e\u0000\u0000\u01e0\u01d7\u0001\u0000\u0000\u0000\u01e0\u01db"+
		"\u0001\u0000\u0000\u0000\u01e1X\u0001\u0000\u0000\u0000\u01e2\u01e3\u0005"+
		".\u0000\u0000\u01e3\u01e4\u0005.\u0000\u0000\u01e4\u01e5\u0005.\u0000"+
		"\u0000\u01e5Z\u0001\u0000\u0000\u0000\u01e6\u01e8\u0007\u0001\u0000\u0000"+
		"\u01e7\u01e6\u0001\u0000\u0000\u0000\u01e8\u01e9\u0001\u0000\u0000\u0000"+
		"\u01e9\u01e7\u0001\u0000\u0000\u0000\u01e9\u01ea\u0001\u0000\u0000\u0000"+
		"\u01ea\\\u0001\u0000\u0000\u0000\u01eb\u01ed\u0007\u0001\u0000\u0000\u01ec"+
		"\u01eb\u0001\u0000\u0000\u0000\u01ed\u01ee\u0001\u0000\u0000\u0000\u01ee"+
		"\u01ec\u0001\u0000\u0000\u0000\u01ee\u01ef\u0001\u0000\u0000\u0000\u01ef"+
		"\u01f0\u0001\u0000\u0000\u0000\u01f0\u01f4\u0005.\u0000\u0000\u01f1\u01f3"+
		"\u0007\u0001\u0000\u0000\u01f2\u01f1\u0001\u0000\u0000\u0000\u01f3\u01f6"+
		"\u0001\u0000\u0000\u0000\u01f4\u01f2\u0001\u0000\u0000\u0000\u01f4\u01f5"+
		"\u0001\u0000\u0000\u0000\u01f5\u0200\u0001\u0000\u0000\u0000\u01f6\u01f4"+
		"\u0001\u0000\u0000\u0000\u01f7\u01f9\u0007\u0004\u0000\u0000\u01f8\u01fa"+
		"\u0007\u0005\u0000\u0000\u01f9\u01f8\u0001\u0000\u0000\u0000\u01f9\u01fa"+
		"\u0001\u0000\u0000\u0000\u01fa\u01fc\u0001\u0000\u0000\u0000\u01fb\u01fd"+
		"\u0007\u0001\u0000\u0000\u01fc\u01fb\u0001\u0000\u0000\u0000\u01fd\u01fe"+
		"\u0001\u0000\u0000\u0000\u01fe\u01fc\u0001\u0000\u0000\u0000\u01fe\u01ff"+
		"\u0001\u0000\u0000\u0000\u01ff\u0201\u0001\u0000\u0000\u0000\u0200\u01f7"+
		"\u0001\u0000\u0000\u0000\u0200\u0201\u0001\u0000\u0000\u0000\u0201^\u0001"+
		"\u0000\u0000\u0000\u0202\u0203\u0005\'\u0000\u0000\u0203\u0204\t\u0000"+
		"\u0000\u0000\u0204\u0205\u0005\'\u0000\u0000\u0205`\u0001\u0000\u0000"+
		"\u0000\u0206\u020a\u0005\"\u0000\u0000\u0207\u0209\b\u0006\u0000\u0000"+
		"\u0208\u0207\u0001\u0000\u0000\u0000\u0209\u020c\u0001\u0000\u0000\u0000"+
		"\u020a\u0208\u0001\u0000\u0000\u0000\u020a\u020b\u0001\u0000\u0000\u0000"+
		"\u020b\u020d\u0001\u0000\u0000\u0000\u020c\u020a\u0001\u0000\u0000\u0000"+
		"\u020d\u020e\u0005\"\u0000\u0000\u020eb\u0001\u0000\u0000\u0000\u020f"+
		"\u0213\u0003\u0003\u0001\u0000\u0210\u0212\u0003\u0005\u0002\u0000\u0211"+
		"\u0210\u0001\u0000\u0000\u0000\u0212\u0215\u0001\u0000\u0000\u0000\u0213"+
		"\u0211\u0001\u0000\u0000\u0000\u0213\u0214\u0001\u0000\u0000\u0000\u0214"+
		"d\u0001\u0000\u0000\u0000\u0215\u0213\u0001\u0000\u0000\u0000\u0216\u0217"+
		"\u0005;\u0000\u0000\u0217f\u0001\u0000\u0000\u0000\u0218\u0219\u0005{"+
		"\u0000\u0000\u0219h\u0001\u0000\u0000\u0000\u021a\u021b\u0005}\u0000\u0000"+
		"\u021bj\u0001\u0000\u0000\u0000\u021c\u021d\u0005,\u0000\u0000\u021dl"+
		"\u0001\u0000\u0000\u0000\u021e\u021f\u0005:\u0000\u0000\u021fn\u0001\u0000"+
		"\u0000\u0000\u0220\u0221\u0005=\u0000\u0000\u0221p\u0001\u0000\u0000\u0000"+
		"\u0222\u0223\u0005(\u0000\u0000\u0223r\u0001\u0000\u0000\u0000\u0224\u0225"+
		"\u0005)\u0000\u0000\u0225t\u0001\u0000\u0000\u0000\u0226\u0227\u0005["+
		"\u0000\u0000\u0227v\u0001\u0000\u0000\u0000\u0228\u0229\u0005]\u0000\u0000"+
		"\u0229x\u0001\u0000\u0000\u0000\u022a\u022b\u0005?\u0000\u0000\u022bz"+
		"\u0001\u0000\u0000\u0000\u022c\u022d\u0005|\u0000\u0000\u022d\u022e\u0005"+
		"|\u0000\u0000\u022e|\u0001\u0000\u0000\u0000\u022f\u0230\u0005&\u0000"+
		"\u0000\u0230\u0231\u0005&\u0000\u0000\u0231~\u0001\u0000\u0000\u0000\u0232"+
		"\u0233\u0005|\u0000\u0000\u0233\u0080\u0001\u0000\u0000\u0000\u0234\u0235"+
		"\u0005^\u0000\u0000\u0235\u0082\u0001\u0000\u0000\u0000\u0236\u0237\u0005"+
		"&\u0000\u0000\u0237\u0084\u0001\u0000\u0000\u0000\u0238\u0239\u0005=\u0000"+
		"\u0000\u0239\u023a\u0005=\u0000\u0000\u023a\u0086\u0001\u0000\u0000\u0000"+
		"\u023b\u023c\u0005!\u0000\u0000\u023c\u023d\u0005=\u0000\u0000\u023d\u0088"+
		"\u0001\u0000\u0000\u0000\u023e\u023f\u0005<\u0000\u0000\u023f\u0240\u0005"+
		"<\u0000\u0000\u0240\u008a\u0001\u0000\u0000\u0000\u0241\u0242\u0005>\u0000"+
		"\u0000\u0242\u0243\u0005>\u0000\u0000\u0243\u008c\u0001\u0000\u0000\u0000"+
		"\u0244\u0245\u0005+\u0000\u0000\u0245\u008e\u0001\u0000\u0000\u0000\u0246"+
		"\u0247\u0005-\u0000\u0000\u0247\u0090\u0001\u0000\u0000\u0000\u0248\u0249"+
		"\u0005*\u0000\u0000\u0249\u0092\u0001\u0000\u0000\u0000\u024a\u024b\u0005"+
		"/\u0000\u0000\u024b\u0094\u0001\u0000\u0000\u0000\u024c\u024d\u0005%\u0000"+
		"\u0000\u024d\u0096\u0001\u0000\u0000\u0000\u024e\u024f\u0005*\u0000\u0000"+
		"\u024f\u0250\u0005=\u0000\u0000\u0250\u0098\u0001\u0000\u0000\u0000\u0251"+
		"\u0252\u0005/\u0000\u0000\u0252\u0253\u0005=\u0000\u0000\u0253\u009a\u0001"+
		"\u0000\u0000\u0000\u0254\u0255\u0005%\u0000\u0000\u0255\u0256\u0005=\u0000"+
		"\u0000\u0256\u009c\u0001\u0000\u0000\u0000\u0257\u0258\u0005+\u0000\u0000"+
		"\u0258\u0259\u0005=\u0000\u0000\u0259\u009e\u0001\u0000\u0000\u0000\u025a"+
		"\u025b\u0005-\u0000\u0000\u025b\u025c\u0005=\u0000\u0000\u025c\u00a0\u0001"+
		"\u0000\u0000\u0000\u025d\u025e\u0005<\u0000\u0000\u025e\u025f\u0005<\u0000"+
		"\u0000\u025f\u0260\u0005=\u0000\u0000\u0260\u00a2\u0001\u0000\u0000\u0000"+
		"\u0261\u0262\u0005>\u0000\u0000\u0262\u0263\u0005>\u0000\u0000\u0263\u0264"+
		"\u0005=\u0000\u0000\u0264\u00a4\u0001\u0000\u0000\u0000\u0265\u0266\u0005"+
		"&\u0000\u0000\u0266\u0267\u0005=\u0000\u0000\u0267\u00a6\u0001\u0000\u0000"+
		"\u0000\u0268\u0269\u0005^\u0000\u0000\u0269\u026a\u0005=\u0000\u0000\u026a"+
		"\u00a8\u0001\u0000\u0000\u0000\u026b\u026c\u0005|\u0000\u0000\u026c\u026d"+
		"\u0005=\u0000\u0000\u026d\u00aa\u0001\u0000\u0000\u0000\u026e\u026f\u0005"+
		"+\u0000\u0000\u026f\u0270\u0005+\u0000\u0000\u0270\u00ac\u0001\u0000\u0000"+
		"\u0000\u0271\u0272\u0005-\u0000\u0000\u0272\u0273\u0005-\u0000\u0000\u0273"+
		"\u00ae\u0001\u0000\u0000\u0000\u0274\u0275\u0005<\u0000\u0000\u0275\u00b0"+
		"\u0001\u0000\u0000\u0000\u0276\u0277\u0005>\u0000\u0000\u0277\u00b2\u0001"+
		"\u0000\u0000\u0000\u0278\u0279\u0005<\u0000\u0000\u0279\u027a\u0005=\u0000"+
		"\u0000\u027a\u00b4\u0001\u0000\u0000\u0000\u027b\u027c\u0005>\u0000\u0000"+
		"\u027c\u027d\u0005=\u0000\u0000\u027d\u00b6\u0001\u0000\u0000\u0000\u027e"+
		"\u027f\u0005.\u0000\u0000\u027f\u00b8\u0001\u0000\u0000\u0000\u0280\u0281"+
		"\u0005-\u0000\u0000\u0281\u0282\u0005>\u0000\u0000\u0282\u00ba\u0001\u0000"+
		"\u0000\u0000\u0283\u0284\u0005~\u0000\u0000\u0284\u00bc\u0001\u0000\u0000"+
		"\u0000\u0285\u0286\u0005!\u0000\u0000\u0286\u00be\u0001\u0000\u0000\u0000"+
		"\u000e\u0000\u00c3\u00c7\u00d3\u00e1\u01e0\u01e9\u01ee\u01f4\u01f9\u01fe"+
		"\u0200\u020a\u0213\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
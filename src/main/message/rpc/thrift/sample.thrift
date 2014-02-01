namespace java org.horiga.study.test.rpc.message

// ----------------------------------------------------
// enums
// ----------------------------------------------------

enum MessageStatusCode
{
  OK                = 0,      // success
  INVALID_MESSAGE   = 4000,   //
  UNAUTHORIZED      = 4010,   //
  UNKNOWN_MESSAGE   = 4040,   //
  FAILED            = 5000,   //
  BUSY              = 5030,   //
}

// exception
exception RpcError
{
  1: i32 msgid,
  2: string message,
  3: i32 code,
}

struct MsgHeader {
  1: string name,
  2: optional i32 txid,
}

struct Ping
{
  1: i32 msgid,
  2: string message,
}

struct Pong
{
  1: i32 msgid,
  2: string message,
}

struct Person
{
  1: i32 id,
  2: string name,
  3: i32 age,
}

struct Friends
{
  1: i32 total,
  2: list<Person> data,
  3: i32 itemsPerPage,
}

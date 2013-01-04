//
//  BaseJsonService.h
//  EmbaClientForiPhone
//
//  Created by wuzhu on 12-6-28.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "ASIHTTPRequestDelegate.h"
#import "UserVO.h"

#define CINT_PAGE_SIZE [NSNumber numberWithInt:20]

@interface BaseJsonService : NSObject <ASIHTTPRequestDelegate>
{
    id _delegate;
    NSInteger _tag;
    NSString *_commandName;
    NSString *_suffix;
}

-(id)initWithDelegate:(id)delegate tag:(NSInteger)ktag;

+(NSString *)getDevID;

+(UserVO *)getUserInfo;

-(void)setAssetsFileInfo:(NSString *)kcommandName suffix:(NSString *)ksuffix;

-(void) getData:(NSString *) json;

-(void) getData:(NSString *) json url:(NSString *)url;


-(void) getData:(NSString *) json url:(NSString *)url process:(BOOL)showprocess;

-(void) getData:(NSString *) json url:(NSString *)url process:(BOOL)showprocess processcontent:(NSString *)showprocesscontent;

-(void) sendServiceFailInfo:(NSString*)msg;
-(void) sendServiceSucessInfo:(NSString*)conent;
@end

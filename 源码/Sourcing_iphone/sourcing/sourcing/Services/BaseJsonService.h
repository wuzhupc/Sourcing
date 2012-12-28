//
//  BaseJsonService.h
//  EmbaClientForiPhone
//
//  Created by wuzhu on 12-6-28.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface BaseJsonService : NSObject

+(void) getData:(NSString *) json delegate:(id)delegate tag:(NSInteger)mtag;

+(void) getData:(NSString *) json url:(NSString *)url delegate:(id)delegate tag:(NSInteger)mtag;


+(void) getData:(NSString *) json url:(NSString *)url delegate:(id)delegate process:(BOOL)showprocess tag:(NSInteger)mtag;

+(void) getData:(NSString *) json url:(NSString *)url delegate:(id)delegate process:(BOOL)showprocess processcontent:(NSString *)showprocesscontent tag:(NSInteger)mtag;

+(void) sendServiceFailInfo:(id)delegate msg:(NSString*)msgconent tag:(NSInteger)mtag;
@end

//
//  JsonParser.h
//  EMBAClient
//
//  Created by shan on 12-4-26.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "ResponseVO.h"

#define CSTR_TAG_RET  @"ret"
#define CSTR_TAG_DATA @"data"

@interface JsonParser : NSObject

+ (ResponseVO* )parseJsonToResponse:(NSString *) jsonString;
+(id) parseDataJson:(NSData *)jsonData respVO:(ResponseVO **)krespVO;
+(id) parseStrJson:(NSString *)jsonStr respVO:(ResponseVO **)krespVO;
+(id) parseJsonToEntity:(NSString *)jsonStr respVO:(ResponseVO **)krespVO  ref:(NSString *)kref;
+(id) parseJsonToList:(NSString *)jsonStr respVO:(ResponseVO **)krespVO  ref:(NSString *)kref;
@end
